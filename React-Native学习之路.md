#React-Native 技术栈

* 原生Android/IOS
* ECMAScript
* ES6
* React
* Redux
* React-Redux
* React-Native

##ECMAScript学习笔记
####数组


##ES6学习笔记
##React学习笔记
环境要求：Node >= 6 npm 5.2.0+

###安装React
**React是一个声明式的，高效的，并且灵活的用于构建用户界面的 JavaScript 库。**

推荐使用 Yarn 或 npm 来管理前端依赖。

使用Yarn安装
	
	yarn init
	yarn add react react-dom
	
使用Yarn安装

	npm init
	npm install --save react react-dom
	
**注意：**为了防止潜在的不兼容性，所有的 react 包应该使用相同的版本 （包括 react，react-dom，react-test-renderer 等）。
JS
1. 判断 js 类型的方式
    a.typeof 可以判断出'string','number','boolean','undefined','symbol'
             但判断 typeof(null) 时值为 'object';
             判断数组和对象时值均为 'object'
    b.instanceof 原理是 构造函数的 prototype 属性是否出现在对象的原型链中的任何位置
    c.Object.prototype.toString.call() 常用于判断浏览器内置对象,对于所有基本的数据类型都能进行判断，即使是 null 和 undefined
    d.Array.isArray(obj) 判断是否是数组。

### JSX
**从本质上讲，JSX 只是为 React.createElement(component, props, ...children) 函数提供的语法糖。**

### Component

### props

### state

### 生命周期 

### 事件

### Context

### Fragment

### Portals

### 条件渲染

### 表单

### 高阶组件（Higher-Order Components）
具体来说，高阶组件是一个**函数**，能够接受一个组件并返回一个新的组件。组件是将props转化成UI，然而高阶组件将一个组价转化成另外一个组件。
### 边界错误
**错误边界是 React  16引入的组件**，它可以在子组件树的任何位置捕获 JavaScript 错误，记录这些错误，并显示一个备用 UI ** ，而不是使整个组件树崩溃。 错误边界(Error Boundaries) 在渲染，生命周期方法以及整个组件树下的构造函数中捕获错误。
#####自定义边界错误
在类组件定义了 <a>componentDidCatch(error, info)</a> 的新生命周期方法，它将成为一个错误边界
### 一致性比较
Diffing 算法

1. 当比较不同的两个树，React 首先比较两个根元素。根据根元素的类型不同，它有不同的行为，React 将会销毁原先的树并重写构建新的树。

	销毁时：之前的 DOM 节点将销毁，实例组件执行 <a>componentWillUnmount()</a><br/>
	新建时：新的 DOM 节点将会插入 DOM 中。组件将会执行 <a>componentWillMount()</a> 以及 <a>componentDidMount()</a> 。与之前旧的树相关的 state 都会丢失。

2. 当比较两个相同类型的 React DOM 元素时，React 检查它们的属性（attributes），保留相同的底层 DOM 节点，只更新反生改变的属性（attributes),在处理完当前 DOM 节点后，React 会递归处理子节点。

3. 当一个组件更新的时候，组件实例保持不变，以便在渲染中保持state。React会更新组件实例的属性来匹配新的元素，并在元素实例上调用 <a>componentWillReceiveProps()</a> 和 <a>componentWillUpdate()</a>。接下来， render() 方法会被调用并且diff算法对上一次的结果和新的结果进行递归。

4. 默认情况下，当递归一个 DOM 节点的子节点时，React 只需同时遍历所有的孩子基点同时生成一个改变当它们不同时。当给子元素末尾添加一个元素，在两棵树之间转化中性能就不错,如果在开始处插入一个节点也是这样简单地实现，那么性能将会很差。

5. 为了解决这个问题，React 支持一个 key 属性（attributes）。当子节点有了 key ，React 使用这个 key 去比较原来的树的子节点和之后树的子节点。




##Redux学习笔记
###Redux的三大原则：

* **单一数据源：**整个应用的 state 被储存在一棵 object tree 中，并且这个 object tree 只存在于唯一一个 store 中。
* **State是只读的：**惟一改变 state 的方法就是触发 action，action 是一个用于描述已发生事件的普通对象。
* **使用纯函数来执行修改：**为了描述 action 如何改变 state tree ，你需要编写 reducers

###对象
* **Action** 是把数据从应用传到 store 的有效载荷。它是 store 数据的唯一来源。一般来说你会通过 store.dispatch() 将 action 传到 store。

	action 结构：首先action是 JavaScript 对象，string类型的 type字段是必须的。
	
	原则：们应该尽量减少在 action 中传递的数据
* **Reducer** reducer 就是一个纯函数（不要修改 state），接收旧的 state 和 action，返回新的 state。在 default 情况下返回旧的 state。
	####reducer中禁止
	1. 修改传入参数；
	2. 执行有副作用的操作，如 API 请求和路由跳转；
	3. 调用非纯函数，如 Date.now() 或 Math.random()。

	
			(previousState, action) => newState
	
	<font color='#f00'>注意</font>:每个 reducer 只负责管理全局 state 中它负责的一部分。每个 reducer 的 state 参数都不同，分别对应它管理的那部分 state 数据。
* **Store** Store 不是类。它只是有几个方法的对象。Store 就是把action	和 reducer 联系维持应用的 state；<br/>
Store 方法
	* getState()
	* dispatch(action)
	* subscribe(listener)
	* replaceReducer(nextReducer)
	
	**Redux 应用只有一个单一的 store**

	1. 维持应用的 state；
	2. 提供 getState() 方法获取 state；
	3. 提供 dispatch(action) 方法更新 state；
	4. 通过 subscribe(listener) 注册监听器;
	5. 通过 subscribe(listener) 返回的函数注销监听器
####通过createStore创建store
	
			import { createStore } from 'redux'
			import reducer from './reducers'
			const action ={ type:'ActionType,
				....
			}
			
			let store = createStore(reducer)
			
			store.dispatch(action)//改变状态
			
			const unsubscriber = store.subscribe(litener)
			
			function litener(){
				const state = store.getState()
			}
			
			unsubscriber().
			
	

* **数据流** 严格的单向数据流是 Redux 架构的设计核心。

	Redux 应用中数据的生命周期遵循下面 4 个步骤：
	
	1. 调用 store.dispatch(action)。
	2. Redux store 调用传入的 reducer 函数。
	3. 根 reducer 应该把多个子 reducer 输出合并成一个单一的 state 树。
	4. Redux store 保存了根 reducer 返回的完整 state 树。
* **API**
	
	createStore(reducer, [preloadedState], [enhancer])
	
	combineReducers(reducers)
	
	applyMiddleware(...middlewares)
	
	bindActionCreators(actionCreators, dispatch)
	
	compose(...functions) 

## React-Native

### React Navigation <https://reactnavigation.org/docs/hello-react-navigation.html>

导航组建位于 ‘react-navigation’ 包中，所以要使用先安装

	yarn add react-navigation
	// or with npm
	// npm install --save react-navigation
	
开始使用：
####StackNavigator:栈式导航组件
作用：1.页面之间的跳转，2.即管理导航历史

StackNavigator是一个返回React组件的function，一般来说StackNavigator返回的组建作为应用的根组件。使用如下：

	// In App.js in a new project
	
	import { StackNavigator } from 'react-navigation';
	import { HomeScreen } from './HomeScreen';
	
	export default StackNavigator({
	  Home: {
	    screen: HomeScreen,//应用展示的第一个页面
	  },
	  Details: {
     	screen: DetailsScreen,
	  	},
	}，
	{
    	initialRouteName: 'Home',
    	/* 统一配置导航样式 */
	    navigationOptions: {
	      headerStyle: {
	        backgroundColor: '#f4511e',
	      },
	      headerTintColor: '#fff',
	      headerTitleStyle: {
	        fontWeight: 'bold',
	      },
	    },
  	});
  	
  	this.props.navigation.navigate('RouteName'，{ /* params go here */ })//跳转到指定界面，RouteName必须在StackNavigator的参数种配置
  	this.props.navigation.goBack();//关闭当前页面出栈。
  	this.props.navigation.state.params//获取跳转参数

  	
1. React Navigation为React Native提供导航组建，其中包含了IOS和安卓的页面跳转时的手势和动画。
2. StackNavigator是一个返回React组件的function，接受路径配置对象作和一些操作对象做为参数，返回的组建不接受props。
3. route Config key为routeName（Home，Details）value为见面的相应设置，screen 对应的页面组件，一旦配置，默认会有this.props.navigation.

#####单独设置某个页面的导航样式：
 
	static navigationOptions = {
	    title: 'Home',//导航标题
	    headerStyle: {//样式
	      backgroundColor: '#f4511e',
	    },
	    headerTintColor: '#fff',
	    headerTitleStyle: {
	      fontWeight: 'bold',
	    },
	};
#####覆盖统一配置的导航样式：
	static navigationOptions = ({ navigation, navigationOptions }) => {
	    const { params } = navigation.state;
	    return {
		      title: params ? params.otherParam : 'A Nested Details Screen',
		      /* These values are used instead of the shared configuration! */
		      headerStyle: {
		        backgroundColor: navigationOptions.headerTintColor,
		      },
		      headerTintColor: navigationOptions.headerStyle.backgroundColor,
	    };
 	};
 	
##### 自定义的组建来替代默认的
	 static navigationOptions = {
	    // headerTitle instead of title
	    headerTitle: <LogoTitle />,//中间
	    headerRight: <ReaderRightButton>,//右边
	    headerLeft:<ReaderLeftButton>//左边
  	};
##### 开启一个全屏的Modal 

##### TabNavigator

	// You can import Ionicons from @expo/vector-icons if you use Expo or
	// react-native-vector-icons/Ionicons otherwise.
	import Ionicons from 'react-native-vector-icons/Ionicons';
	import { TabNavigator, TabBarBottom } from 'react-navigation';
	
	export default TabNavigator(
	  {
	    Home: { screen: HomeScreen },//页面配置
	    Settings: { screen: SettingsScreen },
	  },
	  {
	    navigationOptions: ({ navigation }) => ({
	      tabBarIcon: ({ focused, tintColor }) => {//设置tab 的图标和标题
	        const { routeName } = navigation.state;
	        let iconName;
	        if (routeName === 'Home') {
	          iconName = `ios-information-circle${focused ? '' : '-outline'}`;
	        } else if (routeName === 'Settings') {
	          iconName = `ios-options${focused ? '' : '-outline'}`;
	        }
	
	        // You can return any component that you like here! We usually use an
	        // icon component from react-native-vector-icons
	        return <Ionicons name={iconName} size={25} color={tintColor} />;
	      },
	    }),
	    tabBarOptions: {//选中与非选中颜色
	      activeTintColor: 'tomato',
	      inactiveTintColor: 'gray',
	    },
	    tabBarComponent: TabBarBottom,//组建
	    tabBarPosition: 'bottom',//位置
	    animationEnabled: false,//是否有转场动画
	    swipeEnabled: false,//是否支持手势滑动
	  }
	); 	


##### DrawerNavigator

	const MyApp = DrawerNavigator({
	  Home: {
	    screen: MyHomeScreen,
	  },
	  Notifications: {
	    screen: MyNotificationsScreen,
	  },
	});
	
	static navigationOptions = {
	    drawerLabel: 'Notifications',
	    drawerIcon: ({ tintColor }) => (
	      <Image
	        source={require('./notif-icon.png')}
	        style={[styles.icon, {tintColor: tintColor}]}
	      />
	    ),
  	};
	
	this.props.navigation.navigate('DrawerOpen'); // 打开 drawer
	this.props.navigation.navigate('DrawerClose'); // 关闭 drawer
	this.props.navigation.navigate('DrawerToggle');//切换
	
#### SwitchNavigator
switchnavigator的应用场景是流程只显示一次的界面。（例如登录注册）

	import { StackNavigator, SwitchNavigator } from 'react-navigation';
	
	// Implementation of HomeScreen, OtherScreen, SignInScreen, AuthLoadingScreen
	// goes here.
	
	const AppStack = StackNavigator({ Home: HomeScreen, Other: OtherScreen });
	const AuthStack = StackNavigator({ SignIn: SignInScreen });
	
	export default SwitchNavigator(
	  {
	    AuthLoading: AuthLoadingScreen,
	    App: AppStack,
	    Auth: AuthStack,
	  },
	  {
	    initialRouteName: 'AuthLoading',
	  }
	);


#### 兼容iPhoneX
	import { SafeAreaView } from 'react-navigation';
	
	class App extends Component {
	  render() {
	    return (
	      <SafeAreaView style={styles.container}>
	        {...}
	      </SafeAreaView>
	    );
	  }
	}

#### 状态栏设置
对于StackNavigator 和 DrawerNavigator是比较简单的，通过直接在相应设置就可以达到目的
	
	import { StatusBar } from ‘react-native’
	class Screen1 extends React.Component {
		  render() {
		    return (
		      <SafeAreaView style={[styles.container, { backgroundColor: '#6a51ae' }]}>
		        <StatusBar
		          barStyle="light-content"
		          backgroundColor="#6a51ae"
		        />
		        <Text style={[styles.paragraph, { color: '#fff' }]}>
		          Light Screen
		        </Text>
		        <Button
		          title="Next screen"
		          onPress={() => this.props.navigation.navigate('Screen2')}
		          color={isAndroid ? "blue" : "#fff"}
		        />
		      </SafeAreaView>
		    );
		  }
		}
对于TabNavigator相对复杂，分两步实现：

1. 只在初始界面使用StatusBar
2. tab激活时改变StatusBar的配置

		class TabScreen extends React.Component {
		  componentDidMount() {//添加监听来改变设置
		    this._navListener = this.props.navigation.addListener('didFocus', () => {
		      StatusBar.setBarStyle('light-content');
		      isAndroid && StatusBar.setBackgroundColor('#6a51ae');
		    });
		  }
		
		  componentWillUnmount() {//移除监听
		    this._navListener.remove();
		  }
		
		  ...
		}


#### Android Back 键处理

	import { BackHandler } from ‘react-native’
	class ScreenWithCustomBackBehavior extends React.Component {
	  componentDidMount() {
	    BackHandler.addEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
	  }
	
	  componentWillUnmount() {
	    BackHandler.removeEventListener('hardwareBackPress', this.onBackButtonPressAndroid);
	  }
	
	  onBackButtonPressAndroid = () => {
	    if (this.isSelectionModeEnabled()) {
	      this.disableSelectionMode();
	      return true;
	    } else {
	      return false;
	    }
	  };
	}

#### 在任意组建中使用
在渲染时间navigation传递给自定义组件，以便深层嵌套的组建可以使用navigation
	
	<MyBackButton navigation={this.props.navigation} />.
	
#### Deep linking

#### 页面统计

#### Redux迁移
#### API
1. 对于screen组建 navigation 有以下props：<br/>
	this.props.navigation<br/>
		navigate（routeName, params, action）//or <br/>
			navigation.navigate({routeName, params, action, key})<br/>
		goBack（routeName【nullable】）<br/>
		addListener（key，function）key的值：willBlur willFocus didBlur didFocus<br/>
		isFocused（）return screen是否获取焦点了<br/>
		state   object {routeName key Params}<br/>
		setParams(obj) <br/>
		getParam('key')<br/>
		dispatch(navigateAction)<br/>
		
		import { NavigationActions } from 'react-navigation';
		const navigateAction = NavigationActions.navigate({
		  routeName: 'Profile',
		  params: {},
		  // navigate can have a nested navigate action that will be run inside the child router
		  action: NavigationActions.navigate({ routeName: 'SubProfileRoute' }),
		});
		this.props.navigation.dispatch(navigateAction);

	

	