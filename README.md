OGC Schemas compiled with JAXB2
==============================

This repository is a clone of the ogc project (http://java.net/projects/ogc svn repo: https://svn.java.net/svn/ogc~svn ) with some tweaks that I found usefull/necessary.

<swe:values>
===========
The file [sos/1.0.0/schema/src/test/samples/sosInsertObservation2.xml](/nigelb/OGC_JAXB/blob/master/sos/1.0.0/schema/src/test/samples/sosInsertObservation2.xml) contains the xml fragment
```xml
        <om:result xsi:type="swe:DataArrayPropertyType">
            <swe:DataArray>
                <swe:elementCount>
                    <swe:Count>
                        <swe:value>1</swe:value>
                    </swe:Count>
                </swe:elementCount>
                <swe:elementType name="Components" xlink:type="simple">
                    <swe:DataRecord>
                        <swe:field name="Time" xlink:type="simple">
                            <swe:Time definition="http://www.opengis.net/def/uom/ISO-8601/0/Gregorian"/>
                        </swe:field>
                        <swe:field name="Temperature" xlink:type="simple">
                            <swe:Quantity definition="urn:semat:sensor:6607:temperature">
                                <swe:uom code="Cel" xlink:type="simple"/>
                            </swe:Quantity>
                        </swe:field>
                    </swe:DataRecord>
                </swe:elementType>
                <swe:encoding xlink:type="simple">
                    <swe:TextBlock blockSeparator="&#10;" decimalSeparator="." tokenSeparator=","/>
                </swe:encoding>
                <swe:values xlink:type="simple">2012-01-11T12:02:53.000+10:00,24.918117229129667
                </swe:values>
            </swe:DataArray>
        </om:result>
```
specifically the fragment
```xml
                <swe:values xlink:type="simple">2012-01-11T12:02:53.000+10:00,24.918117229129667
                </swe:values>
```               
which has the schema element:
```xml
  <xs:complexType name="DataValuePropertyType">
		<xs:annotation>
			<xs:documentation>Use to point or include data values inline</xs:documentation>
		</xs:annotation>
		<xs:complexContent>
			<xs:extension base="xs:anyType">
				<xs:attribute name="recordCount" type="xs:positiveInteger"/>
				<xs:attributeGroup ref="gml:AssociationAttributeGroup"/>
			</xs:extension>
		</xs:complexContent>
	</xs:complexType>
```
The problem is that the class generated for this this (schema element above) ends up defined like this:
```java
      .
      .
      .
      
      @XmlAnyElement
      protected List<Element> any;
      .
      .
      .
```
Unfortunately a DOM [Text](http://docs.oracle.com/javase/1.4.2/docs/api/org/w3c/dom/Text.html) Node is a DOM Node instead of a DOM Element.
So if we were to unmarshal [sos/1.0.0/schema/src/test/samples/sosInsertObservation2.xml](/nigelb/OGC_JAXB/blob/master/sos/1.0.0/schema/src/test/samples/sosInsertObservation2.xml) the `any` element would be `null`.
This also leaves us unable to populate this field via the generated JAXB classes without doing something ridiculous like this:

```java
        InsertObservation insert = new InsertObservation();
        
        .
        .
        .
        

        //Marshal the request into a DOM
        mar.marshal(insert, new DOMBuilder(doc));

        //Build a XPath query to locate the node to insert the data into.
        XPath xpath = XPathFactory.newInstance().newXPath();
        xpath.setNamespaceContext(OGCNamespaceMapper.sos.mapper());
        XPathExpression exp = xpath.compile("/sos:InsertObservation/om:Observation/om:result/swe:DataArray/swe:values");
        Node node = (Node) exp.evaluate(doc, XPathConstants.NODE);

        //Insert the data into the nofe found by the XPath query.
        node.appendChild(doc.createTextNode("2012-01-11T12:02:53.000+10:00,24.918117229129667\n"));
```
So I tweaked the [sweCommon/1.0.1/schema/src/main/java/net/opengis/swe/v_1_0_1/DataValuePropertyType.java](/nigelb/OGC_JAXB/blob/master/sweCommon/1.0.1/schema/src/main/java/net/opengis/swe/v_1_0_1/DataValuePropertyType.java) file to be:
```java
    .
    .
    .
    
    @XmlMixed
    @XmlAnyElement
    protected List<Object> any;
    
    .
    .
    .
```  
Which allows one to do this instead:
```java
	((DataArrayPropertyType) ins.getObservation().getResult()).getDataArray().getValues().getAny().add("2012-01-11T12:02:53.000+10:00,24.918117229129667\n");
```