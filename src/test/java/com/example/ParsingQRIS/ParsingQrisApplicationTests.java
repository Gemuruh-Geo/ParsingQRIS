package com.example.ParsingQRIS;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class ParsingQrisApplicationTests {
	@Autowired
	private ParsingQRIS parsingQRIS;

	@Test
	void contextLoads() {
	}
	@Test
	public void testGetAmount() {
		String qris = "00020101021226660014ID.LINKAJA.WWW011893600911002134900102152011130911280020303UME51450015ID.OR.GPNQR.WWW0215ID20210981903520303UME520454995802ID5906kai-vm6007Jakarta6105401176217011312124776878575303360540421006304E98F";
		Map<String, String[]> result = parsingQRIS.doParse(qris);

		String amount = result.get("54")[1];
		Assertions.assertEquals(amount, "2100");

		String qris2 = "00020101021226590013ID.KASPRO.WWW011893600812300000000002090000000000303UMI520458125303360540410005802ID5909BASO JONO6007JAKARTA6105103106236012538771243042616128734223560703A01630459C3";
		Map<String, String[]> result2 = parsingQRIS.doParse(qris2);

		String amount2 = result2.get("54")[1];
		Assertions.assertEquals(amount2, "1000");
	}
}
