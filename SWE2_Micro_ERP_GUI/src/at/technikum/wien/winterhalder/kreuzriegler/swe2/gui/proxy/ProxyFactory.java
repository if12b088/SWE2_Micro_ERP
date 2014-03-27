package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.ProxyMock;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.ProxyWebService;

public class ProxyFactory {

	private final static boolean MOCK = true;
	
	public static IContactProxy create(){
		if(MOCK){
			return new ProxyMock();
		}else{
			return new ProxyWebService();
		}
	}
}
