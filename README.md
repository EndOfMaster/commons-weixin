# commons-weixin
![image](https://img.shields.io/badge/maven-1.0.4-green.svg)
## http和https请求双模式
## 基本接口
1. 获取access_token
2. 获取微信服务器IP地址
3. 获取jsapiTicket
## 用户接口
1. 普通获取用户基本信息
2. 通过确认授权的方式登录和获取信息
## 卡券接口
1. 开通子商户接口
2. 获取卡券开放类目查询接口
3. 卡券相关专用简单图片上传接口
4. 标准媒体图片上传接口
5. 创建子商户接口
6. 创建门店接口
7. 创建卡券接口
8. 修改卡券库存接口
9. 删除卡券接口
10. 查询卡券状态
11. 查询用户领取的单张卡券状态
## 消息接口
1. 发送模版消息
2. 接收微信推送(可以记录接收消息日志)
3. 子商户审核通知
4. 门店审核通知
5. 卡券审核通知
6. 卡券核销通知
7. 卡券删除通知
8. 卡券转赠通知
9. 卡券领取通知

## Maven Repository
    https://maven.pkg.github.com/EndOfMaster/maven-repo
### Maven Import

```xml
 <dependency>
   <groupId>com.endofmaster</groupId>
   <artifactId>commons-weixin</artifactId>
   <version>x.y.z</version>
   <type>pom</type>
 </dependency>
```
### Gradle Import
```groovy
 compile 'com.endofmaster:commons-weixin:x.y.z'
```