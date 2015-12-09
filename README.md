# jittagornp.me

profile of jittagornp <br/>
page : http://jittagornp.me/

## how to use
- install java
```shell
$ sudo apt-get install python-software-properties
$ sudo add-apt-repository ppa:webupd8team/java
$ sudo apt-get update
$ sudo apt-get install oracle-java8-installer
```
- set $PATH
```shell
$ sudo api-get install vim
$ sudo vi /etc/environment
```
add JAVA_HOME=[java path such as /usr/lib/jvm/java-8-oracle]

- install maven
```shell
$ sudo api-get install maven
```
- install git
```shell
$ sudo api-get install git
```
- pull code
```shell
$ git pull https://github.com/jittagornp/jittagornp.me.git master
```
- build & run spring-boot
```shell
$ mvn clean install -U
$ mvn spring-boot:run
```
