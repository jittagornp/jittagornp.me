# jittagornp.me

profile of jittagornp <br/>
page : http://jittagornp.me/

## How to use (Linux Ubuntu Server)
1) install java
```shell
$ sudo apt-get install python-software-properties
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```
2) set $PATH
```shell
$ sudo api-get install vim
$ sudo vi /etc/environment
```
add ```JAVA_HOME=[java path installed such as /usr/lib/jvm/java-8-oracle]``` to bottom page

3) install maven
```shell
$ sudo api-get install maven
```
4) install git
```shell
$ sudo api-get install git
```
5) pull code
```shell
$ mkdir me
$ cd me
$ git init
$ git pull https://github.com/jittagornp/jittagornp.me.git master
```
6) build & run spring-boot
```shell
$ mvn clean install -U
$ mvn spring-boot:run
```
