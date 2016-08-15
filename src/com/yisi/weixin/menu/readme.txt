自定义菜单-->自定义菜单创建接口

自定义菜单接口可实现多种类型按钮，如下：
	1、click：点击推事件
	2、view：跳转URL
	3、scancode_push：扫码推事件
	4、scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框
	5、pic_sysphoto：弹出系统拍照发图
	6、pic_photo_or_album：弹出拍照或者相册发图
	7、pic_weixin：弹出微信相册发图器
	8、location_select：弹出地理位置选择器
	9、media_id：下发消息（除文本消息）
	10、view_limited：跳转图文消息URL

请注意：3到8的所有事件，仅支持微信iPhone5.4.1以上版本

该包中的按钮类型只包含1和2
