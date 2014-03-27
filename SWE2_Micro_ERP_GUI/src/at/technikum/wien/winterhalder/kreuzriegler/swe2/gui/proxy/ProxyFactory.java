package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.ContactProxyMock;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.ContactWebService;

public class ProxyFactory {

	private final static boolean MOCK = true;
	
	public static IContactProxy createContactProxy(){
		if(MOCK){
			return new ContactProxyMock();
		}else{
			return new ContactWebService();
		}
	}
}
