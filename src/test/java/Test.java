import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.hy.ArmenianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParserBase;
import org.apache.lucene.search.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args){
        String queryString = "!message:(test test1) && user: \\\\-1";
        parseFilter(queryString);

        queryString = "test: false && message:(test test1) || message1:(test test1)";
        parseFilter(queryString);


        queryString = "test: false || !message:(test test1) || !message1:(test test1)";
        parseFilter(queryString);

        queryString = "test: false || message:(test test1) || !message1:(test test1)"; // 可以解析正确
        parseFilter(queryString);

        queryString = "test: false && message:(test test1) || !message1:(test test1)"; // 前两个都是+号 后一个是！
        parseFilter(queryString);

        queryString = "test: false || !message:(test test1)";
        parseFilter(queryString);

        queryString = "test: test || !message:(test test1)";
        parseFilter(queryString);

        queryString = "message:(test test1) && user: \\\\-1 || !test: test";
        parseFilter(queryString);

        queryString = "((!message:(test test1)) && user: \\\\-1) || !test: test";
        parseFilter(queryString);

    }

    public static void parseFilter(String queryString){
        QueryParserBase parser = new QueryParser("_all", new WhitespaceAnalyzer());
        try {
            Query query = parser.parse(queryString);
            if (query instanceof BooleanQuery){
                Iterator<BooleanClause> iterator = ((BooleanQuery) query).iterator();
                while(iterator.hasNext()){
                    BooleanClause clause = iterator.next();
                    Query query1 = clause.getQuery();
                    if (query1 instanceof TermRangeQuery){
                        String lowerTerm = new String(((TermRangeQuery) query1).getLowerTerm().bytes);
                        Pattern pattern = Pattern.compile("([1-9]\\d*\\.?\\d*)|(0\\.\\d*[1-9])");
                        if (pattern.matcher(lowerTerm).matches()){
                            System.out.println("lowerTerm is number");
                        }else{
                            System.out.println("lowerTerm is not number");
                        }
                        String upperTerm = new String(((TermRangeQuery) query1).getUpperTerm().bytes);
                        if (pattern.matcher(upperTerm).matches()){
                            System.out.println("upperTerm is number");
                        }else{
                            System.out.println("upperTerm is not number");
                        }

                    }else if (query1 instanceof TermQuery){
                        System.out.println(query1.toString());
                    }else if (query1 instanceof BooleanQuery){
                        Iterator<BooleanClause> booleanClauseIterator = ((BooleanQuery) query1).iterator();
                        while(booleanClauseIterator.hasNext()) {
                            BooleanClause clause1 = booleanClauseIterator.next();
                            System.out.print(clause1.toString());
                            if (booleanClauseIterator.hasNext()) {
                                switch (clause1.getOccur().name()) {
                                    case ("SHOULD"):
                                        System.out.print(" || ");
                                        break;
                                    case ("MUST"):
                                        System.out.print(" && ");
                                        break;
                                }
                            }else{

                            }
                        }
                    }

                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
