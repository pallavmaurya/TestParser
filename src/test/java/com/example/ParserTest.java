package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.NoSuchFileException;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class ParserTest {
    private static final String NO_SUCH_FILE_EXISTS = "Please provide correct relative file path, no such file exists: ";
    @Mock
    private PrintWriter printWriter;

    @InjectMocks
    private Parser parser;

    @Before
    public void setUp() {
        parser = new Parser("src/test/resources/small.in", printWriter);
    }

    @Test
    public void shouldThrowNoSuchFileException() throws IOException {
        try {
            parser = new Parser("test.in", printWriter);
            parser.parse();
        } catch (NoSuchFileException exception) {
            assertEquals(exception.getMessage(), NO_SUCH_FILE_EXISTS);
        }
    }

    @Test
    public void shouldProcess() throws IOException {
        parser.parse();
        Mockito.verify(printWriter).println("Sentence 1,and,Dr.,Jekyll,met,Mr.,Ms.,outside,Smith");
        Mockito.verify(printWriter).println("Sentence 2,he,shocking,shouted,was,What,你这肮脏的掠夺者,停在那儿");
        Mockito.verify(printWriter).println("Sentence 3,a,because,Chinese,couldn't,I,isn't,mother,my,perhaps,tongue,understand,word");
        Mockito.verify(printWriter).println("Sentence 4,around,furious,he,I,just,marching,Mr.,standing,there,was,was,watching,Young");
        Mockito.verify(printWriter).println("Sentence 5,anger,at,directing,he,his,me,was,Why");
        Mockito.verify(printWriter).println("Sentence 6,about,did,I,know,Little,that");
        Mockito.verify(printWriter).println("Sentence 7,and,and,Baltic,banking,capital,in,international,investment,is,leading,Markets,markets,Nordea,Nordic,operator,partner,regions,Sea,the,the");
        Mockito.verify(printWriter).println("Sentence 8,are,connecting,door,global,located,markets,next,the,to,to,We,you,you");
        Mockito.verify(printWriter).println("Sentence 9,a,and,combine,complete,expertise,financial,global,local,of,portfolio,provide,services,solutions,strength,to,We,with,with,you");
        Mockito.verify(printWriter).println("Sentence 10,and,currencies,diversified,have,in,in,liquidity,local,most,Nordics,of,offer,one,outstanding,product,ranges,strongest,the,the,We");
        Mockito.verify(printWriter).println("Sentence 11,access,all,an,best,But,capital,dedicated,experts,facets,in,in,manner,markets,more,of,of,offer,possible,serving,significantly,team,the,to,to,unequalled,we,you,you");
        Mockito.verify(printWriter).println("Sentence 12,a,a,and,and,At,combination,customer,expertise,financial,for,gives,global,have,local,Markets,Nordea,of,of,opportunity,our,rare,services,solutions,strength,the,to,us,use,variety,we,which,wide,you");
        Mockito.verify(printWriter).println("Sentence 13,a,a,a,all,and,and,as,as,be,But,can,challenge,currencies,diversified,excellent,fact,financial,finding,give,hard,have,have,huge,importantly,In,in,in,liquidity,local,matter,might,most,no,Nordics,of,of,ours,outstanding,product,range,ready,serve,specialists,strong,team,the,time,to,too,we,we,what,you,you,your,you’d");

    }

}
