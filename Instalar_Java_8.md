**INSTALAR O JAVA 8 NO LINUX:**

- Primeiro desistale tudo:

[Tutorial para desinstalar o Java 8](https://askubuntu.com/questions/84483/how-to-completely-uninstall-java)

--------------------------------------------------------------------------------------------------------------------------

- Baixar o java 8, no terminal faça os seguintes comandos:

```
wget --no-check-certificate -c --header "Cookie: oraclelicense=accept-securebackup-cookie"
http://download.oracle.com/otn-pub/java/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/
jdk-8u191-linux-x64.tar.gz
```

```
sudo tar -zxvf jdk-8u191-linux-x64.tar.gz -C /opt
```

- Abrir o bashrc como sudo no nano e colar as linhas a seguir no fim do arquivo:

```
sudo nano /etc/bash.bashrc
```

```
export JAVA_HOME=/opt/jdk1.8.0_191
```

```
export PATH=$PATH:$JAVA_HOME/bin
```

- Salva e fecha o arquivo:

```
source /etc/bash.bashrc
```

- Definir o Oracle JDK 11 como a versão padrão do Java:

```
sudo update-alternatives --install "/usr/bin/java" "java" "/opt/jdk1.8.0_191/bin/java" 1
```

```
sudo update-alternatives --install "/usr/bin/javac" "javac" "/opt/jdk1.8.0_191/bin/javac" 1
```

```
sudo update-alternatives --set "java" "/opt/jdk1.8.0_191/bin/java"
```

```
sudo update-alternatives --set "javac" "/opt/jdk1.8.0_191/bin/javac"
```