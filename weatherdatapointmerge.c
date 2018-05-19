#include <stdio.h>
#include <json-c/json.h>
#include <string.h>
#include <time.h>

char* timeToString(struct tm *t);

int main(int argc, char **argv)
{
//    json_object  *temperatureobj, *windobj, *windspeedobj, *winddirecobj,*cloudsobj, *visibilityobj, *precipitationobj, *cityobj, *humidityobj, *pressureobj, *weatherobj, *lastupdateobj,*openweatherobj, *coordobj;
    json_object *temperature, *tempheader, *tempbody, *tempacqui, *tempschema, *tempeffectime, *temptemp, *temptemp_min, *temptemp_max;
json_object *humidity, *humiditydata;
    json_object *dobj,*darray, *dval;

    int userid, humiddata;

    double temp, temp_min, temp_max, version;
    json_object *headerid, *sourcename, *source_creatime, *namespace, *tempunit, *humidityunit, *datetime;

    FILE *tempdatapoint, *humiditydatapoint;
    char temperaturedata[1024], humiditydata[1024];
    tempdatapoint =fopen("tempdatapoint.txt","r");
    humidtydatapoint =fopen("humidity.txt","r");

	 struct tm *t;
	 time_t timer;

    if(tempdatapoint==NULL){
	printf("파일 읽기  실패\n");
	return 0;
    }

    while((fgets(temperaturedata, 1024, tempdatapoint) !=NULL)||fgets(humiditydata, 1024,humiditydatapoint) !=NULL)
    {
	timer = time(NULL);    // 현재 시각을 초 단위로 얻기
        t = localtime(&timer);

	puts(temperaturedata);
	puts(humiditydata);

	temperature = json_tokener_parse(temperaturedata);
	humidity = json_tokener_parse(humiditydata);
	tempheader = json_object_object_get(temperaturedata, "header");
	tempbody = json_object_object_get(temperaturedata, "body");

//	humidityheader = json_object_object_get(humiditydata, "header");
	humiditypbody = json_object_object_get(humiditydata, "body");

	//temp header

	tempacqui = json_object_object_get(tempheader, "acquisition_provenance");
	tempschema = json_object_object_get(tempheader, "schema_id");

	headerid = json_object_object_get(tempheader, "id");
	
	sourcename = json_object_object_get(tempacqui, "source_name");
	source_creatime = json_object_object_get(tempacqui, "source_creation_date_time");

	
	dval = json_object_object_get(headerid, "user_id");	
	userid = json_object_get_int(dval);

	namespace = json_object_object_get(tempschema, "namespace");
	

	dval = json_object_object_get(tempschema, "version");	
	version= json_object_get_double(dval);

	//temp body
	tempeffectime = json_object_object_get(tempbody, "effective_time_frame");
	datetime = json_object_object_get(tempeffectime,"date_time");

	temptemp =json_object_object_get(tempbody, "temp");

	tempunit = json_object_object_get(temptemp, "unit");
	dval = json_object_object_get(temptemp, "value");	
	temp = json_object_get_double(dval);
	

	temptemp_min =json_object_object_get(tempbody, "temp_min");
 
	dval = json_object_object_get(temptemp_min, "value");	
	temp_min= json_object_get_double(dval);



	temptemp_max =json_object_object_get(tempbody, "temp_min");
 
	dval = json_object_object_get(temptemp_max, "value");	
	temp_min= json_object_get_double(dval);

	//humidity

	humidity =json_object_object_get(humiditybody, "humidity");
 	humidityunit = json_object_object_get(humidity, "unit");

	dval = json_object_object_get(humidity, "value");	
	humiddata= json_object_get_int(dval);


	//datapoint generate

	json_object *myobj, *headerobj, *dataobj, *schemaobj, *effectimeobj, *bodyobj, *tempobj, *temp_maxobj, *temp_minobj, *humidity1obj, *pressure1obj, *sunrise1obj, *sunset1obj *temperatureobj;

	myobj = json_object_new_object();
	headerobj = json_object_new_object();
	dataobj   = json_object_new_object();
	schemaobj   = json_object_new_object();
	effectimeobj   = json_object_new_object();
	bodyobj   = json_object_new_object();
	temperatureobj   = json_object_new_object();
	tempobj   = json_object_new_object();
	temp_maxobj   = json_object_new_object();
	temp_minobj =  json_object_new_object();
	humidity1obj =  json_object_new_object();
	pressure1obj =  json_object_new_object();
	sunset1obj =  json_object_new_object();
	sunrise1obj =  json_object_new_object();

	//header 영역
	json_object_object_add(headerobj, "id", json_object_new_string(json_object_get_string(headerid)));
	json_object_object_add(headerobj, "creation_date_time", json_object_new_string(timeToString(t)));
	json_object_object_add(myobj, "header", headerobj);

	// data { ..., nameInfo { ..., } phoneNumber [ ..., ] } 영역 
	json_object_object_add(dataobj, "source_name",json_object_new_string(json_object_get_string(sourcename)));
	json_object_object_add(dataobj, "source_creation_date_time", json_object_new_string(json_object_get_string(source_creatime)));

	json_object_object_add(headerobj, "acquisition_provenance", dataobj);
	json_object_object_add(headerobj, "user_id", json_object_new_int(userid));

	json_object_object_add(schemaobj, "namespace", json_object_new_string(json_object_get_string(namespace)));
	json_object_object_add(schemaobj, "name", json_object_new_string("weather_merge"));
	json_object_object_add(schemaobj, "version", json_object_new_string("1.0"));

	json_object_object_add(headerobj, "schema_id", schemaobj);

	json_object_object_add(effectimeobj, "date_time", json_object_new_string(json_object_get_string(datetime)));

	json_object_object_add(bodyobj, "effective_time_frame", effectimeobj);

	json_object_object_add(tempobj, "unit", json_object_new_string(json_object_get_string(tempunit)));
	json_object_object_add(tempobj, "value", json_object_new_double(temp));
	json_object_object_add(temperatureobj, "temp", tempobj);

	json_object_object_add(temp_minobj, "unit", json_object_new_string(json_object_get_string(tempunit)));
	json_object_object_add(temp_minobj, "value", json_object_new_double(temp_min));
	json_object_object_add(temperatureobj, "temp_min", temp_minobj);

	json_object_object_add(temp_maxobj, "unit", json_object_new_string(json_object_get_string(tempunit)));
	json_object_object_add(temp_maxobj, "value", json_object_new_double(temp_max));
	json_object_object_add(temperatureobj, "temp_max", temp_maxobj);

	json_object_object_add(bodyobj, "body", temperatureobj);


	json_object_object_add(humidity1obj, "unit", json_object_new_string(json_object_get_string(humidityunit)));
	json_object_object_add(humidity1obj, "value", json_object_new_int(humiddata));
	json_object_object_add(bodyobj, "humidity", humidity1obj);

	json_object_object_add(myobj, "body", bodyobj);

	printf("myobj.to_string()=%s\n", json_object_to_json_string(myobj));
    }
    fclose(tempdatapoint);
    return 0;
}
