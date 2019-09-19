import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class SAXSchemaValidatorExample{
    public static void main(String[] a){

        String schemaName = "myCustomer.xsd";
        String xmlName = "myCustomer.xml";  // valid xml
        Schema schema = loadSchema( schemaName );

        validateXml( schema, xmlName );

        String xmlName2 = "myCustomer2.xml"; // not valid xml
        validateXml( schema, xmlName2 );
    }

    private static void validateXml(Schema schema, String xmlName){
        try{
            Validator validator = schema.newValidator();
            System.out.println();
            System.out.println("Validator Class: " + validator.getClass().getName());

            SAXSource source = new SAXSource( new InputSource( new java.io.FileInputStream( xmlName ) ) );

            validator.validate( source );
            System.out.println("Validation passed");

        } catch( Exception e ){
            System.out.println( e.toString() );
        }
    }

    private static Schema loadSchema( String name ){
        Schema schema = null;
        try{
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance( language );
            schema = factory.newSchema(new File( name ) );
        } catch( Exception e ){
            System.out.println( e.toString() );
        }
        return schema;
    }
}
