package net.sf.JRecord.cbl2json.zTest.json2cbl;

import java.io.IOException;

public interface ICopyBookTestData {

	String getJsonData();

	String data2json(int tagFormat) throws IOException;

	String readExpectedJsonFile() throws IOException;

	String getCopybookName();

}