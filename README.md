# LeaBlog
一款利用java作为后台重写的Leanote 博客模块，只需要运行jar即可获得属于自己Leanote 博客。Leanote 无需特殊配置。

博客地址:[Ericwyn's Blog](https://blog.meetwhy.com)
 

## 配置文件相关
 - jar 目录下 `leablog.cfg`文件
 
        API_URL = https://localhost:9000/api    ; leanote api地址
        MAIL = admin@leanote.com ; leanote 登录账号
        PASSWORD = password ; leanote 登录密码
        TIME_INTERVE = 10   ;   博客列表缓存的有效期，以分钟为单位
        API_TOKEN = null; 自动增加和保存的配置项，该行可不填写
        USER_ID = null; 自动增加和保存的配置项，该行可不填写
        USER_NAME = null; 自动增加和保存的配置项，该行可不填写