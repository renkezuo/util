package com.renke.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyMain {
	public static void main(String[] args) {
//		String str = "485454502F312E3120323030204F4B0D0A5365727665723A206E67696E782F312E362E330D0A446174653A204672692C203038204A756C20323031362030343A32303A303520474D540D0A436F6E74656E742D547970653A20746578742F68746D6C3B636861727365743D5554462D380D0A5472616E736665722D456E636F64696E673A206368756E6B65640D0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A566172793A204163636570742D456E636F64696E670D0A5365742D436F6F6B69653A204A53455353494F4E49443D37343046424344353339463844303530313146363934373545353034314336453B20506174683D2F78646D2D61646D696E2F3B20487474704F6E6C790D0A436F6E74656E742D4C616E67756167653A207A682D434E0D0A436F6E74656E742D456E636F64696E673A20677A69700D0A0D0A3231390D0A1F8B0800000000000003AD553B72DB3010AD9D99DC01416F41FE14294835EE73850C042C498CF1E100A0645D206DD2BACF2992EB6492C92DB220231BE2478967CC822480E57B780FBBCBE29D74221E5A204D347AF3F64DF1F4042ED3D840E44434DC078825ED6275F99EA6F9A8A286CDAFC7EFBF1F3F156C18E1B456F69E24C0924678884C844049E3A12A297B90E6924BA32CF3105CE70584B49E4610577DA4075DD2100F1A4203107BA61EF23C44E5B981BDF3F78C0BE831B7CEC5103D6F57C8378F4D584267CD20145FB74E1E70EAA2906A4784E62194D4541F25D85A77692B04AFF16A742DDD14CAD42478B12052195EA3560C5DB5B6C670860C7FF112E68850387B649B654CCEFF37670A9E67ED992BE70DE1222A674F363F9C9374DAD5CA528259D03859D2D685FE50D2B7C7EBA2E8746697B26D378919746895C569B5BE1A433D436ABE058D460DCF53C29CBCE8E9B28CA3E3AD200B51B8F52E80FF80694289C57B361EC939A223B75A5A1A0BB97E65212DE61EA6B35C14D3EE716DD0915E97F6F90209374B20C98F7145A85DCABF7F3A7F3D38BF03AFAAC39D934FDE67339362C84F7786FBC0B13866EB4D38631CF6166EA53302D9589FC494EC958C4D49AFD66B6C45A0EA061BD9ED7A5A862779F502EB6ECF5A97DB14BAAD51717AAA3794ECB8EE302B7F7EFEF2E3DBD725C03339392D918275A9A39FAA4AF59E779EAC131DBB5281CDB36F84D81BD34FE10F41401398240600000D0A300D0A0D0A";
//		String aaa = "485454502F312E3120323030204F4B0D0A5365727665723A206E67696E782F312E362E330D0A446174653A204672692C203038204A756C20323031362030343A32303A303520474D540D0A436F6E74656E742D547970653A20746578742F68746D6C3B636861727365743D5554462D380D0A5472616E736665722D456E636F64696E673A206368756E6B65640D0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A566172793A204163636570742D456E636F64696E670D0A5365742D436F6F6B69653A204A53455353494F4E49443D37343046424344353339463844303530313146363934373545353034314336453B20506174683D2F78646D2D61646D696E2F3B20487474704F6E6C790D0A436F6E74656E742D4C616E67756167653A207A682D434E0D0A436F6E74656E742D456E636F64696E673A20677A69700D0A0D0A3231390D0A1F8B0800000000000003AD553B72DB3010AD9D99DC01416F41FE14294835EE73850C042C498CF1E100A0645D206DD2BACF2992EB6492C92DB220231BE2478967CC822480E57B780FBBCBE29D74221E5A204D347AF3F64DF1F4042ED3D840E44434DC078825ED6275F99EA6F9A8A286CDAFC7EFBF1F3F156C18E1B456F69E24C0924678884C844049E3A12A297B90E6924BA32CF3105CE70584B49E4610577DA4075DD2100F1A4203107BA61EF23C44E5B981BDF3F78C0BE831B7CEC5103D6F57C8378F4D584267CD20145FB74E1E70EAA2906A4784E62194D4541F25D85A77692B04AFF16A742DDD14CAD42478B12052195EA3560C5DB5B6C670860C7FF112E68850387B649B654CCEFF37670A9E67ED992BE70DE1222A674F363F9C9374DAD5CA528259D03859D2D685FE50D2B7C7EBA2E8746697B26D378919746895C569B5BE1A433D436ABE058D460DCF53C29CBCE8E9B28CA3E3AD200B51B8F52E80FF80694289C57B361EC939A223B75A5A1A0BB97E65212DE61EA6B35C14D3EE716DD0915E97F6F90209374B20C98F7145A85DCABF7F3A7F3D38BF03AFAAC39D934FDE67339362C84F7786FBC0B13866EB4D38631CF6166EA53302D9589FC494EC958C4D49AFD66B6C45A0EA061BD9ED7A5A862779F502EB6ECF5A97DB14BAAD51717AAA3794ECB8EE302B7F7EFEF2E3DBD725C03339392D918275A9A39FAA4AF59E779EAC131DBB5281CDB36F84D81BD34FE10F41401398240600000D0A300D0A0D0A";
//		String eee = "485454502F312E3120323030204F4B0D0A5365727665723A206E67696E782F312E362E330D0A446174653A204672692C203038204A756C20323031362030393A32303A343320474D540D0A436F6E74656E742D547970653A20746578742F68746D6C3B636861727365743D5554462D380D0A5472616E736665722D456E636F64696E673A206368756E6B65640D0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A566172793A204163636570742D456E636F64696E670D0A5365742D436F6F6B69653A204A53455353494F4E49443D41433635323537324145374335443445343332383142333931343035463434453B20506174683D2F78646D2D61646D696E2F3B20487474704F6E6C790D0A436F6E74656E742D4C616E67756167653A207A682D434E0D0A436F6E74656E742D456E636F64696E673A20677A69700D0A0D0A3231390D0A1F3F00000000000003AD553B723F10AD9D99DC01416F413F294835EE733F042C498CF13FA0645D206DD2BA3F92EB6492C92D3F231BE2478967CC82243F7B780FBBCBE29D74221E5A204D347AF3F64DF1F4042ED3D840E444343F3FED6275F99EA6F9A8A286CDAFC7EFBF1F3F156C18E1B456F69E24C0924678884C844049E3A12A297B90E6924B3F3F5C3F84B49E4610577D3F5D3F0F1A4203107B3F3F44E5B981BDF3F73F3FB7CE3F3D6F573F8F4D5842673F145FB74E1E70EAA2906A4784E62194D4541F25D85A77692B04AFF16A742D3FCAD424783F52195EA3560C5DB5B6C6703F7F3FE68850387B649B654C3F37670A9E67ED992B3F3F2A674F363F9C9374DAD5CA5282593F59D2D685FE50D2B7C7EBA2E8746697B26D373F746895C569B5BE1A433D436A3F8D460DCF53C29CBCE8E9B28CA3E3AD200B51B8F52E3F3F4289C57B361E3F3FB75A5A1A0BB97E65212D3FA6B35C14D3EE716DD0915E97F63F09374B20C98F7145A85DCABF7F3A7F3D383FAFAAC39D934FDE67339362C84F7786FBC0B13866EB4D38631C3F6E3F02D9589FC494EC958C4D49AFD66B6C45A0EA061BD9ED7A5A3F793FEB6ECF5A97DB14BAAD51717A3F94ECB8EE302B7F7EFEF2E3DB3F3F39392D918275A9A39FAA4AF59E779EAC131DBB5281CDB36F84D81BD34F3F4140133F0600000D0A";
//		String str = "485454502F312E3120323030204F4B0D0A5365727665723A206E67696E782F312E362E330D0A446174653A204672692C203038204A756C20323031362030393A31343A303620474D540D0A436F6E74656E742D547970653A20746578742F68746D6C3B636861727365743D5554462D380D0A5472616E736665722D456E636F64696E673A206368756E6B65640D0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A566172793A204163636570742D456E636F64696E670D0A5365742D436F6F6B69653A204A53455353494F4E49443D41463641343737333035303745423633433434323042443532343542463444363B20506174683D2F78646D2D61646D696E2F3B20487474704F6E6C790D0A436F6E74656E742D4C616E67756167653A207A682D434E0D0A436F6E74656E742D456E636F64696E673A20677A69700D0A0D0A3231390D0A1F3F08000000000000033F553B723F30103F3F3F3F01416F413F142948353F733F0C042C493F3FA8A2003F645D206D3F3F3F293F3F643F3F2D3F20231B3F473F673F3F243F3F7B780F3F3F3F3F74221E5A204D347AA8AE3F4D3F3F042E3F3F403F44343F073F25A8AA6275A8B43F3FA8B4A1A73F3F3F3F3F3F3F1F3F156C18A8A23F563F3F243F3F46783F4C3F40493F3F2A297B3F3F3F4B3F2CA8AE105C3F053F3F3F4610577DA1E8075D3F100F1A4203107B3F1EA8B03C443F3F3F3FA8AEA1C23F0BA8A831A1A43F3F103D6F573F373F4D5842673F20145FA1A44E1E70A8BA3F3F6A473F3F213F3F541F253F5A77692B043F3F6A742D3F143F3F2478A1C02052195E3F560C5D3F3F3F703F0C7F3F123F3F50387B643F654C3F3F37670A3F67A8AA3F2B3F0DA8A2222A674F363F3F3F743F3F3F523F593F38593F3F3F3F503FA1A43F3F3FA8A874663F3F6D373F1974683F3F693F3F1A433D436A3F053F460D3F533F3F3FA8A8A8A63F3F3F3F3F200B513F3F2E3F3F3F69423F3F7B361E3F393F23A1A45A5A1A0B3F7E65212D3F1E3F3F5C143F3F716D3F3F5E3F3FA8B40209374B203F3F7145A1A75D3F3F7F3A7F3D383F033F3F3F3F3F4F3F67333F623F4F773F3F3FA1C038663F4D38631C3F166E3F33023F583F3F3FA8AC3F3F4D493F3F6B6C453FA8BA061B3FA8AA7A5A3F27793F023F6E3F5A3F3F143F3F51717A3F373FA8AC3F3F302B7F7E3FA8B03F3FA1C1253F3339392D3F3F753F3F3F3F4A3F3F773F3F131D3F523F3F3F6F3F3F1B3F4FA8A20F4140133F240600000D0A";
//		System.out.println(StringHex.decode(str));
//		StringBuilder sb = new StringBuilder("123\r\n\r\n9ABCDE\r\n\r\n======");
//		System.out.println(sb.delete(0,str.indexOf("\r\n\r\n")+4));
//		String ttt = "485454502F312E3120323030204F4B0D0A5365727665723A206E67696E782F312E362E330D0A446174653A204672692C203038204A756C20323031362030393A32373A333920474D540D0A436F6E74656E742D547970653A20746578742F68746D6C3B636861727365743D5554462D380D0A5472616E736665722D456E636F64696E673A206368756E6B65640D0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A566172793A204163636570742D456E636F64696E670D0A5365742D436F6F6B69653A204A53455353494F4E49443D42353839303242433141453843333041453033463632444241383732323835443B20506174683D2F78646D2D61646D696E2F3B20487474704F6E6C790D0A436F6E74656E742D4C616E67756167653A207A682D434E0D0A0D0A3632340D0A3C21646F63747970652068746D6C3E0D0A3C68746D6C3E0D0A3C686561643E0D0A3C6D65746120636861727365743D227574662D38223E0D0A3C7469746C653EE799BBE999863C2F7469746C653E0D0A3C6C696E6B20747970653D22746578742F6373732220687265663D222F78646D2D61646D696E2F7265736F75726365732F6373732F72657365742E637373222072656C3D227374796C657368656574223E0D0A3C6C696E6B20687265663D222F78646D2D61646D696E2F7265736F75726365732F6373736672616D65776F726B2F6163652F6373732F626F6F7473747261702E6D696E2E637373222072656C3D227374796C65736865657422202F3E0D0A3C2F686561643E0D0A0D0A3C626F64793E0D0A093C64697620636C6173733D226D665F64656E676C75223E0D0A20202020093C64697620636C6173733D226D665F746F70223E3C696D67207372633D222F78646D2D61646D696E2F7265736F75726365732F696D616765732F746F702E706E67223E3C2F6469763E0D0A20202020202020203C64697620636C6173733D226D665F636F6E223E0D0A2020202020202020093C64697620636C6173733D226D665F7469746C65223E3C696D67207372633D222F78646D2D61646D696E2F7265736F75726365732F696D616765732F7469746C652E706E67223E3C2F6469763E0D0A2020202020202020202020203C666F726D20616374696F6E3D222F78646D2D61646D696E2F61646D696E2F646F6C6F67696E22206D6574686F643D22706F7374223E0D0A202020202020202020202020093C756C20636C6173733D226D665F696E707574223E0D0A20202020202020202020202020202020093C6C6920636C6173733D226D665F6C693031223E0D0A2020202020202020202020202020202020202020093C6C6162656C3E3C2F6C6162656C3E0D0A2020202020202020202020202020202020202020202020203C696E70757420747970653D22746578742220636C6173733D226D665F696E7075743031222069643D22757365724E616D6522206E616D653D22757365724E616D65223E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020203C6C6920636C6173733D226D665F6C693032223E0D0A2020202020202020202020202020202020202020093C6C6162656C3E3C2F6C6162656C3E0D0A2020202020202020202020202020202020202020202020203C696E70757420747970653D2270617373776F72642220636C6173733D226D665F696E7075743031222069643D2270776422206E616D653D22707764223E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020203C6C6920636C6173733D226D665F6C693033223E0D0A2020202020202020202020202020202020202020202020203C64697620636C6173733D226D665F646976223E3C696E70757420747970653D22746578742220636C6173733D226D665F696E7075743032222069643D22766572696679436F646522206E616D653D22766572696679436F6465223E3C2F6469763E0D0A2020202020202020202020202020202020202020202020203C64697620636C6173733D226D665F79616E223E3C696D67207372633D222F78646D2D61646D696E2F636F6D6D6F6E2F72616E646F6D636F64652F61646D696E222077696474683D2231303022206865696768743D223430223E3C2F6469763E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020203C6C6920636C6173733D226D665F6C693034223E0D0A2020202020202020202020202020202020202020202020203C696E70757420747970653D227375626D69742220636C6173733D226D665F696E7075743033222076616C75653D22E68F90E4BAA4223E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020200D0A202020202020202020202020202020203C2F756C3E0D0A2020202020202020202020203C2F666F726D3E0D0A20202020202020203C2F6469763E0D0A202020203C2F6469763E0D0A3C2F626F64793E0D0A3C2F68746D6C3E0D0A";
//		String YYY = "485454502F312E3120323030204F4B0D0A5365727665723A206E67696E782F312E362E330D0A446174653A204672692C203038204A756C20323031362030393A32373A333920474D540D0A436F6E74656E742D547970653A20746578742F68746D6C3B636861727365743D5554462D380D0A5472616E736665722D456E636F64696E673A206368756E6B65640D0A436F6E6E656374696F6E3A206B6565702D616C6976650D0A566172793A204163636570742D456E636F64696E670D0A5365742D436F6F6B69653A204A53455353494F4E49443D42353839303242433141453843333041453033463632444241383732323835443B20506174683D2F78646D2D61646D696E2F3B20487474704F6E6C790D0A436F6E74656E742D4C616E67756167653A207A682D434E0D0A0D0A3632340D0A3C21646F63747970652068746D6C3E0D0A3C68746D6C3E0D0A3C686561643E0D0A3C6D65746120636861727365743D227574662D38223E0D0A3C7469746C653EE799BBE999863C2F7469746C653E0D0A3C6C696E6B20747970653D22746578742F6373732220687265663D222F78646D2D61646D696E2F7265736F75726365732F6373732F72657365742E637373222072656C3D227374796C657368656574223E0D0A3C6C696E6B20687265663D222F78646D2D61646D696E2F7265736F75726365732F6373736672616D65776F726B2F6163652F6373732F626F6F7473747261702E6D696E2E637373222072656C3D227374796C65736865657422202F3E0D0A3C2F686561643E0D0A0D0A3C626F64793E0D0A093C64697620636C6173733D226D665F64656E676C75223E0D0A20202020093C64697620636C6173733D226D665F746F70223E3C696D67207372633D222F78646D2D61646D696E2F7265736F75726365732F696D616765732F746F702E706E67223E3C2F6469763E0D0A20202020202020203C64697620636C6173733D226D665F636F6E223E0D0A2020202020202020093C64697620636C6173733D226D665F7469746C65223E3C696D67207372633D222F78646D2D61646D696E2F7265736F75726365732F696D616765732F7469746C652E706E67223E3C2F6469763E0D0A2020202020202020202020203C666F726D20616374696F6E3D222F78646D2D61646D696E2F61646D696E2F646F6C6F67696E22206D6574686F643D22706F7374223E0D0A202020202020202020202020093C756C20636C6173733D226D665F696E707574223E0D0A20202020202020202020202020202020093C6C6920636C6173733D226D665F6C693031223E0D0A2020202020202020202020202020202020202020093C6C6162656C3E3C2F6C6162656C3E0D0A2020202020202020202020202020202020202020202020203C696E70757420747970653D22746578742220636C6173733D226D665F696E7075743031222069643D22757365724E616D6522206E616D653D22757365724E616D65223E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020203C6C6920636C6173733D226D665F6C693032223E0D0A2020202020202020202020202020202020202020093C6C6162656C3E3C2F6C6162656C3E0D0A2020202020202020202020202020202020202020202020203C696E70757420747970653D2270617373776F72642220636C6173733D226D665F696E7075743031222069643D2270776422206E616D653D22707764223E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020203C6C6920636C6173733D226D665F6C693033223E0D0A2020202020202020202020202020202020202020202020203C64697620636C6173733D226D665F646976223E3C696E70757420747970653D22746578742220636C6173733D226D665F696E7075743032222069643D22766572696679436F646522206E616D653D22766572696679436F6465223E3C2F6469763E0D0A2020202020202020202020202020202020202020202020203C64697620636C6173733D226D665F79616E223E3C696D67207372633D222F78646D2D61646D696E2F636F6D6D6F6E2F72616E646F6D636F64652F61646D696E222077696474683D2231303022206865696768743D223430223E3C2F6469763E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020203C6C6920636C6173733D226D665F6C693034223E0D0A2020202020202020202020202020202020202020202020203C696E70757420747970653D227375626D69742220636C6173733D226D665F696E7075743033222076616C75653D22E68F90E4BAA4223E0D0A20202020202020202020202020202020202020203C2F6C693E0D0A20202020202020202020202020202020202020200D0A202020202020202020202020202020203C2F756C3E0D0A2020202020202020202020203C2F666F726D3E0D0A20202020202020203C2F6469763E0D0A202020203C2F6469763E0D0A3C2F626F64793E0D0A3C2F68746D6C3E0D0A";
		
		Calendar c = Calendar.getInstance();
		c.set(2017, 2, 12);
		//周日算做一周第一天
//		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		System.out.println(c.getTime());
//		System.out.println(c.get(Calendar.DAY_OF_WEEK));
//		ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<String,String>();
//		System.out.println(chm.putIfAbsent("123", "111"));
//		System.out.println(chm.putIfAbsent("123", "123"));
//		System.out.println(chm.putIfAbsent("123", "333"));
		
		try {
//			ClassLoader.getSystemClassLoader().loadClass("com.renke.http.Download");
			Class.forName("com.renke.http.Download");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<String> strs = new ArrayList<String>();
		for(int i=0;i<200;i++){
			strs.add("index:"+i);
		}
		System.out.println(strs.get(9));
		strs.subList(0, 10).forEach(System.out::println);
		
		
		Hello hello = new MyMain().new Hello();
		hello.id = "dssd";
		hello.name = "4444";
		System.out.println(hello);
		System.out.println(hello.name);
		hello.setHello(hello);
		System.out.println(hello);
		System.out.println(hello.name);
		
		List<Integer> list = new ArrayList<>(2);
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		int i = Integer.MAX_VALUE;
		System.out.println(i+1 < i);
		
		String str = "hello";
		String str2 = "he" + new String("llo");
		System.out.println(str == str2);
//		for(int i=0;i<10;i++){
//			list.add(i);
//		}
//		List<Integer> list2 = new ArrayList<>(list.subList(0, 6));
//		list2.forEach(System.out::println);
	}
	
	class Hello{
		String id = "123";
		String name = "123123";
		
		public void setHello(Hello x){
			x.name = "bbbb";
		}
	}
}
