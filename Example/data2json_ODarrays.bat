
java -jar ../lib/Cobol2Json.jar -cobol in/ODArray01.cbl -fileOrganisation text ^
                             -input in/ODArray01.txt   ^
                             -output out/ODArray01.json
java -jar ../lib/Cobol2Json.jar -cobol in/invoice.cbl -fileOrganisation text ^
                             -input in/invoice.txt   ^
                             -output out/invoice.json

java -jar ../lib/Cobol2Json.jar -cobol in/ODArray61.cbl -fileOrganisation text ^
                             -input in/ODArray61.txt   ^
                             -output out/ODArray61.json
java -jar ../lib/Cobol2Json.jar -cobol in/ODArray61a.cbl -fileOrganisation text ^
                             -input in/ODArray61.txt   ^
                             -output out/ODArray61a.json


pause                             

