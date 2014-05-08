package at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy;

import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.ContactProxyMock;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.ContactWebService;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.InvoiceProxyMock;
import at.technikum.wien.winterhalder.kreuzriegler.swe2.gui.proxy.impl.InvoiceWebService;

public class ProxyFactory {

	private final static boolean MOCK = true;

	public static IContactProxy createContactProxy() {
		if (MOCK) {
			return new ContactProxyMock();
		} else {
			return new ContactWebService();
		}
	}
	
	public static IInvoiceProxy createInvoiceProxy(){
		if(MOCK){
			return new InvoiceProxyMock();
		}else{
			return new InvoiceWebService();
		}
	}
	
}
