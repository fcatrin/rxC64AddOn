
bajamos del svn el vice_framework, que contiene todas las bibliotecas listas para su compilacion en android, ademas de sus assets, interface java, etc.

Para compilar:

Algunos prerrequisitos (de los que me acuerdo).

ant, java7, autotools, ia32-libs (si estas en un entorno 64 bits para poder correr algunos binarios de 32 bits), paquetes make, gcc, etc.

Preparacion:

Entorno: Ubuntu 14.04.4 LTS
Android SDK : android sdk con build tools 23.0.3
Android NDK: android-ndk-r11c

Variables Entorno:

export PATH=/home/hydefus/android/android-ndk-r11c:$PATH
export PATH=/home/hydefus/android/android-sdk-linux/tools:$PATH
export PATH=/home/hydefus/android/android-sdk-linux/build-tools/23.0.3:$PATH

Descargar vice2.4

git clone https://github.com/lubomyr/vice-2.4

Linkear vice_framework con vice2.4

Vamos al proyecto vice_framework

cd project/jni/application/vice/

creamos link simbolico llamado vice que apunte al port vice android:

ln -s ~/vice-2.4 vice

vamos a la raiz de vice_framework

./build.sh vice

Si no tenemos configurado una llave en el keystore por defecto, no podremos firmar el APK generado, pero esto lo podemos hacer fuera del script sin problemas.

Atte

Max
hydefus@gmail.com

----

Instrucciones adicionales para OSX (sin usar build.sh vice)

El src está actualizado para compilar en OSX

cd project
./build.osx.sh

En una oportunidad la compilación x86 falló porque estaba usando el toolchain de osx
Se arregló eliminando vice-2.4/x86 para que lo volviera a crear
