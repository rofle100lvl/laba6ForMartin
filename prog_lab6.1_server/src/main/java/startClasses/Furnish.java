package startClasses;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Начальный enum ремонта
 */
@XmlType(name = "Furnish")
@XmlEnum
public enum Furnish implements Serializable {
    DESIGNER,
    NONE,
    FINE,
    LITTLE;
}