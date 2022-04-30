//#!/usr/bin/jjs -J-Djava.class.path=../lib/cb2xml.jar:../lib/JRecord.jar:../lib/Jackson.jar:../lib/Cobol2Json.jar

import net.sf.cobolToJson.Cobol2Json

//  ------------------------------------------------------------------
//    Author: Bruce Martin
//    
//   Purpose: Demonstrate converting Cobol-Data-Files to / from Json
//            using jjs - the JavaScript interpretter built into java 8
//
//  ------------------------------------------------------------------


    def constants = Cobol2Json.JR_CONSTANTS
 

    // Create a xml conversion  Object for the DTAR020 cobol copybook
    def dtar020Json= Cobol2Json.newCobol2Json("cobol/DTAR020.cbl")
                             .setFileOrganization(constants.IO_FIXED_LENGTH_RECORDS) // Fixed Length Record
                             .setDialect(constants.FMT_MAINFRAME)                    // Mainframe Cobol (the Default)
                             .setFont("cp037");                                      // US EBCDIC
  
    dtar020Json .cobol2json("in/DTAR020.bin", "out/DTAR020_Groovy.json");            // Convert a DTAR020 file to Json


//  This example demonstrates how convert a Multi-Record file to 'Flat' Json
//  This file has three records PO-Record, Product-Record and Location-Record
//  The field Record-Type determines the type of record
//  *       PO-Record  Record-Type = "H1"
//  *  Product-Record  Record-Type = "D1"
//  * Location-Record  Record-Type = "S1"

    Cobol2Json     .newCobol2Json("cobol/amsPoDownload.cbl")
                                             // Cobol Options
                             .setFileOrganization(constants.IO_UNICODE_TEXT)
                             .setDialect(constants.FMT_FUJITSU)               // Fujitsu Cobol (GNU Cobol would work
                             .setSplitCopybook(constants.SPLIT_01_LEVEL)      // Split the Cobol Copybook on 01 levels
                             .setFont("UTF-8")                                // It is a good idea to set the Character-set

                                             // Tell JRecord how determine which record is which by Testing the Record-Type field      
                             .setRecordSelection("PO-Record",       Cobol2Json.newFieldSelection("Record-Type","H1")) // Record-Type='H1'  
                             .setRecordSelection("Product-Record",  Cobol2Json.newFieldSelection("Record-Type","D1")) // Record-Type='D1'
                             .setRecordSelection("Location-Record", Cobol2Json.newFieldSelection("Record-Type","S1"))

                  .cobol2json("in/Ams_PODownload_20041231.txt", "out/Ams_PODownload_20041231_Groovy_flat.json");



//  This example demonstrates how convert a Multi-Record file to Json
//  This file has three records: PO-Record, Product-Record and Location-Record
//  The field Record-Type determines the type of record
//  *       PO-Record  Record-Type = "H1"
//  *  Product-Record  Record-Type = "D1"
//  * Location-Record  Record-Type = "S1"
//   
//  This example defines the Record hierarchy:
//
//  PO-Record   (has one or more child  Product-Record's
//     !
//     +---- Product-Record (1 or more) -has 1 or more  Location-Record's
//              !
//              +---- Location-Record (1 or more)    

    Cobol2Json     .newCobol2Json("cobol/amsPoDownload.cbl")
                                             // Set Cobol Copybook Options
                             .setFileOrganization(constants.IO_UNICODE_TEXT)
                             .setDialect(constants.FMT_FUJITSU)
                             .setSplitCopybook(constants.SPLIT_01_LEVEL)

                                             // Tell JRecord how determine  which record is which by Testing the Record-Type field         
                             .setRecordSelection("PO-Record",       Cobol2Json.newFieldSelection("Record-Type","H1"))
                             .setRecordSelection("Product-Record",  Cobol2Json.newFieldSelection("Record-Type","D1"))
                             .setRecordSelection("Location-Record", Cobol2Json.newFieldSelection("Record-Type","S1"))

                                             // Define parent - child relationships between Records 
                             .setRecordParent("Product-Record",  "PO-Record")      // The parent of Product-Record is PO-Record          
                             .setRecordParent("Location-Record", "Product-Record")

                                             // Convert file to Json
                  .cobol2json("in/Ams_PODownload_20041231.txt", "out/Ams_PODownload_20041231_Groovy_tree.json");

                  
                  // Testing array elements to make sure thay are not spaces / zero's
                  
   Cobol2Json     .newCobol2Json("cobol/amsPoDownload.cbl")
                                             // Set Cobol Copybook Options
                             .setFileOrganization(constants.IO_UNICODE_TEXT)
                             .setDialect(constants.FMT_FUJITSU)
                             .setSplitCopybook(constants.SPLIT_01_LEVEL)

                                             // Tell JRecord how determine  which record is which by Testing the Record-Type field         
                             .setRecordSelection("PO-Record",       Cobol2Json.newFieldSelection("Record-Type","H1"))
                             .setRecordSelection("Product-Record",  Cobol2Json.newFieldSelection("Record-Type","D1"))
                             .setRecordSelection("Location-Record", Cobol2Json.newFieldSelection("Record-Type","S1"))

                                             // Define parent - child relationships between Records 
                             .setRecordParent("Product-Record",  "PO-Record")      // The parent of Product-Record is PO-Record          
                             .setRecordParent("Location-Record", "Product-Record")
                             
                             .setArrayCheck("location", Cobol2Json.ARRAY_CHECK_BUILDER.newStopAtSpacesZeros())  // Array Elemetn check

                                             // Convert file to Json
                  .cobol2json("in/Ams_PODownload_20041231.txt", "out/Ams_PODownload_20041231_Groovy_tree_arrayCheck.json");
  
                  
   Cobol2Json     .newCobol2Json("cobol/amsPoDownload.cbl")
                                             // Set Cobol Copybook Options
                             .setFileOrganization(constants.IO_UNICODE_TEXT)
                             .setDialect(constants.FMT_FUJITSU)
                             .setSplitCopybook(constants.SPLIT_01_LEVEL)
                             .setTagFormat(constants.RO_MINUS_TO_UNDERSCORE)

                                             // Tell JRecord how determine  which record is which by Testing the Record-Type field         
                             .setRecordSelection("PO-Record",       Cobol2Json.newFieldSelection("Record-Type","H1"))
                             .setRecordSelection("Product-Record",  Cobol2Json.newFieldSelection("Record-Type","D1"))
                             .setRecordSelection("Location-Record", Cobol2Json.newFieldSelection("Record-Type","S1"))

                                             // Define parent - child relationships between Records 
                             .setRecordParent("Product-Record",  "PO-Record")      // The parent of Product-Record is PO-Record          
                             .setRecordParent("Location-Record", "Product-Record")
                             
                             .setArrayCheck("location", Cobol2Json.ARRAY_CHECK_BUILDER.newStopAtSpacesZeros())  // Array Elemetn check

                                             // Convert file to Json
                  .cobol2json("in/Ams_PODownload_20041231.txt", "out/Ams_PODownload_20041231_Groovy_tree_Underscors.json");
                  