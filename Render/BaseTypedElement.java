package Render;

import java.util.stream.Stream;

public class BaseTypedElement {
  public static String[] typeArray = {"int", "long", "float", "double"}; 
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
      default:
        this.typeIndex = -1;
        break;
    }
    return this.typeIndex;
  }

  public void setType(String type) {
    if (Stream.of(BaseTypedElement.typeArray).anyMatch(type.toLowerCase()::equals)) {
      this.type = type.toLowerCase();
      this.typePrefix = this.type.substring(0, 1);
      this.getTypeIndex();
    } else {
      System.out.println("[ ERROR ] Tipo inválido.\r\n" + "Tipo desconhecido: "+ type + "\r\n");
      System.exit(1);
    }
  }

  public void setConversionIndex(int typeIndex) {
    if (4 > typeIndex && 0 <= typeIndex && typeIndex != this.typeIndex) {
      this.conversionTypeIndex = typeIndex;
    } else {
      this.conversionTypeIndex = -1;
    }
  }

  public String getConversionByteCode() {
    if (this.conversionTypeIndex < 0 || this.conversionTypeIndex == this.typeIndex) return "";
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
    
}
