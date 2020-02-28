通配符模式
	将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”只能匹配一个词。
	因此“audit.#”能够匹配到“audit.irs”和“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。