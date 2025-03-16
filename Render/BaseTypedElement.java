package Render;

import java.util.stream.Stream;

public class BaseTypedElement {
  public static String[] typeArray = {"int", "long", "float", "double", "string"}; 
  public static String[] typeArrayPrefix = {"i", "l", "f", "d", "a"}; 
  public static String[] typeArrayByteCodeKey = {"I", "L", "F", "D", "Ljava/lang/String;"}; 
  protected String type;
  protected String typePrefix;
  protected int typeIndex = -1;
  protected int conversionTypeIndex = -1;
  
  
  public int getSlotSize() {
    if ((this.getTypeIndex() & 1) == 1) {
      return 2;
    }
    return 1;
  }

  public int getTypeIndex() {
    if (this.typeIndex >= 0) return typeIndex;

    switch (this.type) {
      case "int":
        this.typeIndex = 0;
        break;
      case "long":
        this.typeIndex = 1;
        break;
      case "float":
        this.typeIndex = 2;
        break;
      case "double":
        this.typeIndex = 3;
        break;
      case "string":
        this.typeIndex = 4;
        break;
      default:
        System.out.println("[ERROR] Tipo desconhecido: " + this.type);
        this.typeIndex = -1;
        break;
    }
    return this.typeIndex;
  }

  public void setType(String type) {
    if (Stream.of(BaseTypedElement.typeArray).anyMatch(type.toLowerCase()::equals)) {
      this.type = type.toLowerCase();
      this.getTypeIndex();
      this.typePrefix = BaseTypedElement.typeArrayPrefix[this.typeIndex];
    } else {
      System.out.println("[ ERROR ] Tipo inválido.\r\n" + "Tipo desconhecido: "+ type + "\r\n");
      System.exit(1);
    }
  }

  public void setConversionIndex(int typeIndex) {
    if (5 > typeIndex && 0 <= typeIndex && typeIndex != this.typeIndex) {
      this.conversionTypeIndex = typeIndex;
    } else {
      this.conversionTypeIndex = -1;
    }
  }

  public String getConversionByteCode() {
    if (this.conversionTypeIndex < 0 || this.conversionTypeIndex == this.typeIndex) return "";
    if (this.typeIndex > 3) {
      System.out.println("[ERROR] Não é possível converter string para númerico.\n Isso implica que alguma operação inválida está sendo executada.\n"+ this.type + " -> "+ this.conversionTypeIndex);
      System.exit(1);
      return "";
    }
    String targetPrefix = BaseTypedElement.typeArray[this.conversionTypeIndex].substring(0, 1);
    if (targetPrefix == this.typePrefix) return "";
    return this.typePrefix + "2" + targetPrefix + "\r\n";
  }

  public String getTypePrefix() {
    return this.typePrefix;
  }

  public String getType() {
    return this.type;
  }

  public String getTypeArrayByteCodeKey() {
    if(this.typeIndex >= 0 && 5 > this.typeIndex) return BaseTypedElement.typeArrayByteCodeKey[this.typeIndex];
    return "";
  }

  public String render() { return "";}; 
}
