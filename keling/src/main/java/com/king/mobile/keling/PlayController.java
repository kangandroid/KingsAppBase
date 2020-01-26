package com.king.mobile.keling;

import com.king.mobile.util.Loker;

import org.fourthline.cling.controlpoint.ControlPoint;
import org.fourthline.cling.model.action.ActionInvocation;
import org.fourthline.cling.model.message.UpnpResponse;
import org.fourthline.cling.model.meta.Device;
import org.fourthline.cling.model.meta.Service;
import org.fourthline.cling.model.types.UDAServiceType;
import org.fourthline.cling.support.avtransport.callback.Pause;
import org.fourthline.cling.support.avtransport.callback.Play;
import org.fourthline.cling.support.avtransport.callback.Seek;
import org.fourthline.cling.support.avtransport.callback.SetAVTransportURI;
import org.fourthline.cling.support.avtransport.callback.Stop;
import org.fourthline.cling.support.renderingcontrol.callback.SetMute;
import org.fourthline.cling.support.renderingcontrol.callback.SetVolume;

public class PlayController {
    /**
     * 控制服务
     */
    private ControlPoint mCp;
    private Service avTransportService;
    private Service renderingControlService;
    private Device mDevice;

    public PlayController(ControlPoint controlPoint, Device device) {
        mCp = controlPoint;
        mDevice = device;
        UDAServiceType avTransport = new UDAServiceType("AVTransport");
        avTransportService = device.findService(avTransport);
        UDAServiceType renderingControl = new UDAServiceType("RenderingControl");
        renderingControlService = device.findService(renderingControl);
    }


    /**
     * 断开
     */
    public void stop() {
        mCp.execute(new Stop(avTransportService) {
            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {

            }

        });
    }

    /**
     * 播放
     */
    public void play(String speed) {
        mCp.execute(new Play(avTransportService, speed) {

            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {
                Loker.e(defaultMsg);
            }

            @Override
            public void success(ActionInvocation invocation) {
                super.success(invocation);
            }
        });
    }

    /**
     * 设置播放源
     */
    public void setSource(String url) {

        mCp.execute(new SetAVTransportURI(avTransportService, url) {

            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {
                Loker.e(defaultMsg);
            }

            @Override
            public void success(ActionInvocation invocation) {
                super.success(invocation);
            }
        });
    }


    /**
     * 设置音量
     *
     * @param volume
     */
    public void setVolume(long volume) {
        mCp.execute(new SetVolume(avTransportService, volume) {

            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {
                Loker.e(defaultMsg);
            }

            @Override
            public void success(ActionInvocation invocation) {
                super.success(invocation);
            }
        });
    }

    /**
     * 静音
     *
     * @param mute
     */
    public void setMute(boolean mute) {
        mCp.execute(new SetMute(avTransportService, mute) {

            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {
                Loker.e(defaultMsg);
            }

            @Override
            public void success(ActionInvocation invocation) {
                super.success(invocation);
            }
        });
    }

    /**
     * 暂停
     * @param device
     */
    public void pause(Device device) {
        mCp.execute(new Pause(renderingControlService) {
            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {

            }

            @Override
            public void success(ActionInvocation invocation) {
                super.success(invocation);
            }
        });
    }

    /**
     * 移动到
     */
    public void seekTo(String relateTimeTarget) {
        mCp.execute(new Seek(renderingControlService, relateTimeTarget) {
            @Override
            public void failure(ActionInvocation invocation, UpnpResponse operation, String defaultMsg) {

            }

            @Override
            public void success(ActionInvocation invocation) {
                super.success(invocation);
            }
        });
    }


    public Device getDevice() {
        return mDevice;
    }
}
