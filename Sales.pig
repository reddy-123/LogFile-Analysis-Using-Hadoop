salesTable = LOAD '/pigdata/Sales.csv' USING PigStorage(',') AS (Transaction_date:chararray,Product:chararray,Price:chararray,Payment_Type:chararray,Name:chararray,City:chararray,State:chararray,Country:chararray,Account_Created:chararray,Last_Login:chararray,Latitude:chararray,Longitude:chararray);


/*B= FILTER salesTable BY Price MATCHES '3600.*';
STORE B INTO 'pigoutput3' USING PigStorage('\t');*/

/*B= FILTER salesTable BY  Payment_Type matches '.*Visa.*';
STORE B INTO 'pigoutput0' USING PigStorage('\t');*/

/*B= FILTER salesTable BY Country matches '.*United States.*';
STORE B INTO 'pigoutput0' USING PigStorage('\t');*/


B= FILTER salesTable BY Price matches '3600.*' and Payment_Type matches '.*Visa.*' and Country matches '.*United States.*';
DUMP B;
/*STORE B INTO 'pigoutput5' USING PigStorage('\t');*/
/*B= FILTER salesTable BY (Price>'5000.*');

B= FILTER salesTable BY (Price>='3000.*');

B= FILTER salesTable BY (Price<='2500.*');

B= FILTER salesTable BY (Price<='1000.*');

DUMP B;*/
/*STORE B INTO 'pigoutput3' USING PigStorage('\t');*/


/*GroupByCountry = GROUP salesTable BY Country;
CountByCountry = FOREACH GroupByCountry GENERATE CONCAT((chararray)$0,CONCAT(':',(chararray)COUNT($1)));
STORE CountByCountry INTO 'tmp' USING PigStorage('\t');
DUMP CountByCountry;*/





/*B = FOREACH salesTable GENERATE Product, Price;
DUMP B;*/
/*
B = FOREACH salesTable GENERATE Payment_Type, City;
DUMP B;

B = FOREACH salesTable GENERATE Country, Account_Created;
DUMP B;

B = FOREACH salesTable GENERATE Name, Last_Login;
DUMP B;

B = FOREACH salesTable GENERATE Latitude, Longitude;
DUMP B;
*/



/*C = DISTINCT salesTable;
DUMP C;*/

/*order_by_data = ORDER salesTable BY Price DESC;
DUMP order_by_data;*/

/*totuple = FOREACH emp_data GENERATE TOTUPLE (Product,Payment_Type,age);*/







