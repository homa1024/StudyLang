
/**
 * java の enum で面白そうな書き方見つけたのでテスト
 *  ・enum にコンストラクタなんてあるのか.
 *  ・列挙定数の中にクラスのインスタンスまで持たせられるのか．
 *  ・idとハンドラの対応付け．
 *  
 *  参考 Qiita やさしい設計～Android編
 *         http://qiita.com/KeithYokoma/items/f19a732aad3beff9387c
 */

/* 実行結果
hoge constructer
fuga constructer
hoge handler
fuga handler
act: FUGA
*/

class EnumTest {
	public static void main(String[] strs) {
		MyAction act = MyAction.valueOf("HOGE");
		act.getmHandler().handle();
		
		act = MyAction.valueOf(200);
		act.getmHandler().handle();
		
		System.out.println("act: " + act);
	}
}

enum MyAction {
	HOGE(100, new HogeHandler()), // HogeHandler等のコンストラクタはプログラム開始時に呼ばれてるみたい
	FUGA(200, new FugaHandler()),
	UNKNOWN(-1, null);
	
	private final int mId;
	private final IHandler mHandler;
	
	private MyAction(final int id, final IHandler handler){ // privateじゃないといけない
	    	mId = id;
		mHandler = handler;
	}
	
//	MyAction valueOf(final String )はenam型で自動で定義される．列挙定数の名前が引数となる 
	public static MyAction valueOf(final int id) { 
	    for (MyAction act : values()) { // values()はenum型で自動で定義
		if (act.getmId() == id) {
		    return act;
		}
	    }
	    return UNKNOWN;
	}
	public int getmId() {
	    return mId;
	}
	public IHandler getmHandler() {
		return mHandler;
	}
	
//	public String toString() はenum型で自動で定義される．列挙定数の名前が返される

}

interface IHandler {
	public void handle();
}
class HogeHandler implements IHandler {
	public HogeHandler() {
		System.out.println("hoge constructer");
	}
	@Override
	public void handle() {
		System.out.println("hoge handler");
	}
}
class FugaHandler implements IHandler {
	public FugaHandler() {
		System.out.println("fuga constructer");
	}
	@Override
	public void handle() {
		System.out.println("fuga handler");
	}
}
