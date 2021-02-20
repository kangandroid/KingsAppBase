inline class Password(var value: String)
// 必须有且只有一个属性在主构造函数中初始化

var securePwd = Password("123456")

// 内联类 可以申明 属性与函数 不能有init代码块 和 幕后字段


//幕后字段 field
var counter = 0
    set(value){
        if(value>=0) field = value
    }





