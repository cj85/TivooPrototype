import html.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.*;

class HtmlCalendar {
    private static final int NROWS = 7;
    private static final int NDAYS = 7;

    public static void main(String argv[]) throws IOException {
	Calendar cal = Calendar.getInstance(Locale.US);
	int first = cal.getFirstDayOfWeek();
	DateFormatSymbols dfs = new DateFormatSymbols(Locale.US);
	String[] weekdays = dfs.getWeekdays();
	String[] months = dfs.getMonths();
	cal.setTime(new Date());

	Tag html = new Tag("html");
	Tag head = new Tag("head");
	head.add(new Tag("link", "rel=stylesheet type=text/css href=./styles.css"));
	Tag title = new Tag("title");
	title.add("HTML Calendar generated by Java HTML generator");
	head.add(title);
	Tag body = new Tag("body");
	Tag main = new Tag("div", "align=center");
	body.add(main);
	Tag h2 = new Tag("h2");
	main.add(h2);
	h2.add("HTML Calendar - an example for the Java HTML generator");
	Tag p = new Tag("h3");
	p.add("Calendar for " + months[cal.get(Calendar.MONTH)]);
	main.add(p);

	Tag table = 
	    new Tag("table", "border=0 cellpadding=3 cellspacing=0 width=500");

	// create seven rows of seven columns each
	Tag header = new Tag("tr");
	for (int j = 0; j < NDAYS; j++) {
	    Tag day = new Tag("th", "class=header width=60");
	    day.add(weekdays[(first++)].substring(0, 3));
	    if (first > NDAYS)
		first = 1;
	    header.add(day);
	    }
	table.add(header);

	// fill table with empty cells for days
	for (int i = 0; i < NROWS-1; i++) {
	    Tag tr = new Tag("tr", "align=center");
	    for (int j = 0; j < NDAYS; j++) {
		Tag cell = new Tag("td", "align=center valign=center bgcolor=#eeeeee");
		cell.add("&nbsp;");
		cell.add("<br>\n");
		Tag fonttag = new Tag("font", "size=+1");
		fonttag.add("&nbsp;");
		cell.add(fonttag);
		cell.add("<br>\n");
		cell.add("&nbsp;");
		tr.add(cell);
	    }
	    table.add(tr);
	}

	// fill int days
	int y = 0;
	int month = cal.get(Calendar.MONTH);
	for (int i = 1; i <= 31; i++) {
	    cal.set(Calendar.DAY_OF_MONTH, i);
	    if (month != cal.get(Calendar.MONTH))
		break;
	    int x = cal.get(Calendar.DAY_OF_WEEK) % NDAYS;
	    y = cal.get(Calendar.WEEK_OF_MONTH);
	    if (x == 0)
		x = NDAYS;
	    x--;
	    Tag cell = new Tag("td", "align=center valign=center");
	    Attributes attrs = cell.getAttributes();
	    if ((x % 2) != 0) {
		attrs.add(new Attribute("bgcolor", "#a0e0e0"));
	    } else {
		attrs.add(new Attribute("bgcolor", "#ccf0f0"));
	    }
	    cell.add("&nbsp;");
	    cell.add("<br>\n");
	    Tag fonttag = new Tag("font", "size=+1");
	    fonttag.add(Integer.toString(i));
	    cell.add(fonttag);
	    cell.add("<br>\n");
	    cell.add("&nbsp;");
	    Tag TRow = (Tag)table.get(y);
	    TRow.set(x, cell);
	}
	// remove surplus rows
	for (int i = NROWS-1; i > y; i--) {
	    table.remove(i);
	}
	main.add(table);
	main.add(new Tag("br", false));
	main.add("&copy; 2000 Cyrille Artho");
	Tag h3 = new Tag("h3");
	h3.add("This page was generated by the ");
	Tag link = new Tag("a", "href=http://artho.com/webtools/java/");
	link.add("Java HTML generator");
	h3.add(link);
	main.add(h3);
	html.add(head);
	html.add(body);
//	System.out.println(html);
	/////////////////////////
	/**/
	FileWriter fw = new FileWriter("./test.html");
    BufferedWriter bw = new BufferedWriter(fw);    
    bw.write(html.toString());
    bw.flush();
    bw.close();
    fw.close();
    }
}
