package crawler.example;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


/**
 * 資料探索練習 Facebook Graph Api Search 
 * 
 * 重點
 * 1. 利用Graph Api調整出需要的資料
 * 2. 取得一組Access Token (試著使用 long term token)
 * 3. 試著用『excel』或任何最簡易的方式，對資料進行探索
 * 
 * @author Abola Lee
 *
 */
public class FacebookExample {
	
	public static void main(String[] args) {
		
		// 遠端資料路徑
		// [query sample]
		// search?fields=name,id,likes,talking_about_count&limit=1000&q=靠北&type=page
		String uri = 
				"https://graph.facebook.com/v2.5"
				+ "/search?q=%e7%b2%89%e7%b5%b2%e5%9c%98&type=page&limit=10&fields=name,id,likes,talking_about_count"
				+ "&access_token=EAACEdEose0cBAOyGDIoLyBxFyyMPs5DoPM8BAHBnoDSk4O59zZCT4p2EQFQ7nV9qPGmyYZBkkdNl1mFV7cVyhdDI899wh4ABB87DJYYixHY7Gm1tXxHiIddEejHZB040YFDN8JcJYbRcBtNZAYFPXQoqlxtFKaDp2AUGDRSE1MZBOrvwiTKtuQAs6J6ijkbkZD";



		// Jsoup select 後回傳的是  Elements 物件
//		[data sample]
//		----
//		{
//			"data": [
//			{
//				"name": "靠北工程師",
//					"id": "1632027893700148",
//					"likes": 174587,
//					"talking_about_count": 188119
//			}
//		}
		Elements elems =
				CrawlerPack.start()
				.getFromJson(uri)
				.select("data");
		
		String output = "id,名稱,按讚數,討論人數\n";
		
		// 遂筆處理
		for( Element data: elems ){
			String id = data.select("id").text();
			String name = data.select("name").text();
			String likes = data.select("likes").text();
			String talking_about_count = data.select("talking_about_count").text();
			
			output += id+",\""+name+"\","+likes+","+talking_about_count+"\n";
		}
		
		System.out.println( output );
	} 
}
