**Mini-Compilador**

***Build Compiler***
 ```
 javacc IntermediareTotalCompiler.jj &&
 javac *.java
```

***Build Code***
 ```
 java IntermediareTotalCompiler < Entrada.txt &&
 java -jar jasmin.jar prog_destino.j
```

***Run Code***
 ```
 java prog_destino
```

Depedências:
- JavaCC: https://javacc.github.io/javacc/
- Jasmin: https://jasmin.sourceforge.net/

Referências:
 - https://javaalmanac.io/bytecode/
 - https://en.wikipedia.org/wiki/List_of_Java_bytecode_instructions
