BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Events" (
	"eventId"	double,
	"eventName"	String,
	"eventDescription"	String,
	"nextEventID1"	double,
	"eventChoice1"	String,
	"nextEventID2"	double,
	"eventChoice2"	String,
	"nextEventID3"	double,
	"eventChoice3"	String,
	PRIMARY KEY("eventId")
);
INSERT INTO "Events" ("eventId","eventName","eventDescription","nextEventID1","eventChoice1","nextEventID2","eventChoice2","nextEventID3","eventChoice3") VALUES (1.0,'Alan','Hello',1.1,'choose 1.1',1.2,'choose 1.2
',1.3,'choose 1.3'),
 (1.1,'Alan','Hello 1.1',1.11,'choose 1.11',1.12,'choose 1.12',1.13,'choose 1.13
'),
 (1.2,'Alan
','Hello 1.2',1.21,'choose 1.21',1.22,'choose 1.22',1.23,'choose 1.22'),
 (1.3,'Alan','Hello 1.3',1.31,'choose 1.31',1.32,'choose 1.32
',1.33,'choose 1.33');
COMMIT;
