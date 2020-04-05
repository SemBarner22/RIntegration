import org.junit.Assert;
import org.junit.Test;

public class ParserTest {
    @Test
    public static void main(String[] args) {
        Assert.assertEquals("filter{(element>10)&(element<20)}%>%map{element}",
                Parser.parse("filter{(element>10)}%>%filter{(element<20)}"));
        Assert.assertEquals("filter{((element+10)>10)}%>%map{((element+10)*(element+10))}",
                Parser.parse("map{(element+10)}%>%filter{(element>10)}%>%map{(element*element)}"));
        Assert.assertEquals("TYPE ERROR",
                Parser.parse("filter{(element+10)}%>%map{((element+10)*(element+10))}"));
        Assert.assertEquals("SYNTAX ERROR",
                Parser.parse("filter{((element+10)>10)&}%>%map{((element+10)*(element+10))}"));
        Assert.assertEquals("filter{(-element<-10)&((-element+10)>10)}%>%map{((element+10)*(element+10))}",
                Parser.parse("filter{(-element<-10)}%>%filter{((-element+10)>10)}%>%map{((element+10)*(element+10))}"));
        Assert.assertEquals("filter{(-element<-10)&((-element+10)>10)}%>%map{element}",
                Parser.parse("filter{(-element<-10)}%>%filter{((-element+10)>10)}"));
        Assert.assertEquals("filter{element=element}%>%map{((element+(10*element))*(element+10))}",
                Parser.parse("map{((element+(10*element))*(element+10))}"));
        System.out.println("Tests have passed successfully");
    }
}