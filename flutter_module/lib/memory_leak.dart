import 'dart:developer';
import 'dart:io';

import 'package:vm_service/utils.dart';
import 'package:vm_service/vm_service.dart';
import 'package:vm_service/vm_service_io.dart';

/// vm_service 在启动的时候会在本地开启一个 WebSocket 服务
///
/// 1 服务 URI 可以在对应的平台中获得：
///   Android 在 FlutterJNI.getObservatoryUri() 中；
///   iOS 在 FlutterEngine.observatoryUrl 中。
/// 2 默认获取的是 http 协议 需要借助 convertToWebSocketUrl方法转化成 ws:// 协议的 URI，
/// 3 有了 URI 之后我们就可以通过vmServiceConnectUri 使用  vm_service 的服务了
/// 4

///
/// vm_service 工具类
class VmServiceHelper {
  VmServiceHelper._internal();

  static get instance => _instance;

  static late final VmServiceHelper _instance = VmServiceHelper._internal();

  Future<Uri?> getServerUri() async {
    ServiceProtocolInfo serviceProtocolInfo = await Service.getInfo();
    // 默认获取的是 http 协议
    return serviceProtocolInfo.serverUri;
  }

  VmService? _vmService;

  /// VmService
  Future<VmService?> getVmService() async {
    if (_vmService == null) {
      final uri = await getServerUri();
      if (uri != null) {
        // 转化成 ws:// 协议的 URI
        Uri url = convertToWebSocketUrl(serviceProtocolUrl: uri);

        _vmService = await vmServiceConnectUri(url.toString()).catchError((error) {
          if (error is SocketException) {
            //dds is enable
            print('vm_service connection refused, Try:');
            print('run \'flutter run\' with --disable-dds to disable dds.');
          }
        });
      }
    }
    return _vmService;
  }

  Future<VM?> getVM() async {
    var vm = await _vmService?.getVM();
    return vm;
  }

  Future<Isolate?> getMainIsolate() async{
    IsolateRef? ref;
    final vm = await getVM();
    if (vm == null) return null;
    vm.isolates?.forEach((isolate) {
      if (isolate.name == 'main') {
        ref = isolate;
      }
    });
    final vms = await getVmService();
    if (ref?.id != null) {
      return vms?.getIsolate(ref!.id!);
    }
    return null;
  }

  ///find a [Library] on [Isolate]
  Future<LibraryRef?> findLibrary(String uri) async {
    Isolate? mainIsolate = await getMainIsolate();
    if (mainIsolate != null) {
      final libraries = mainIsolate.libraries;
      if (libraries != null) {
        for (int i = 0; i < libraries.length; i++) {
          var lib = libraries[i];
          if (lib.uri == uri) {
            return lib;
          }
        }
      }
    }
    return null;
  }

  ///get ObjectId in VM by Object
  Future<String?> getObjectId(dynamic obj) async {
    final library = await findLibrary(_findLibrary);
    if (library == null || library.id == null) return null;
    final vms = await getVmService();
    if (vms == null) return null;
    final mainIsolate = await getMainIsolate();
    if (mainIsolate == null || mainIsolate.id == null) return null;
    Response keyResponse =
    await vms.invoke(mainIsolate.id!, library.id!, 'generateNewKey', []);
    final keyRef = InstanceRef.parse(keyResponse.json);
    String? key = keyRef?.valueAsString;
    if (key == null) return null;
    _objCache[key] = obj;

    try {
      Response valueResponse = await vms
          .invoke(mainIsolate.id!, library.id!, "keyToObj", [keyRef!.id!]);
      final valueRef = InstanceRef.parse(valueResponse.json);
      return valueRef?.id;
    } catch (e) {
      print('getObjectId $e');
    } finally {
      _objCache.remove(key);
    }
    return null;
  }

}

int _key = 0;

/// 顶级函数，必须常规方法，生成 key 用
String generateNewKey() {
  return "${++_key}";
}
