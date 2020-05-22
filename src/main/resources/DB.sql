 CREATE TABLE "NETCRACKER"."EMPLOYEES"
   (	"ID" NUMBER(8,0),
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
	"SURNAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
	"POSITION_ID" NUMBER(8,0),
	"DEPARTMENT_ID" NUMBER(8,0),
	"MANAGER_ID" NUMBER(8,0),
	"EMPLOYMENT_DATE" DATE,
	 CONSTRAINT "EMPLOYEE_PK" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "EMPLOYEES_NETCRACKER_TBS"  ENABLE,
	 CONSTRAINT "FK_DEPARTMENT" FOREIGN KEY ("DEPARTMENT_ID")
	  REFERENCES "NETCRACKER"."DEPARTMENT" ("ID") ENABLE,
	 CONSTRAINT "FK_POSITION" FOREIGN KEY ("POSITION_ID")
	  REFERENCES "NETCRACKER"."POSITION" ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "EMPLOYEES_NETCRACKER_TBS" ;

 CREATE TABLE "NETCRACKER"."DEPARTMENT"
   (	"ID" NUMBER(8,0),
	"NAME" VARCHAR2(200 BYTE) NOT NULL ENABLE,
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "EMPLOYEES_NETCRACKER_TBS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "EMPLOYEES_NETCRACKER_TBS" ;

  CREATE TABLE "NETCRACKER"."POSITION"
   (	"ID" NUMBER(8,0),
	"NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE,
	 PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "EMPLOYEES_NETCRACKER_TBS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "EMPLOYEES_NETCRACKER_TBS" ;


   CREATE SEQUENCE  "NETCRACKER"."DEPARTMENT_SEQUENCE"  MINVALUE 1 MAXVALUE 500 INCREMENT BY 1 START WITH 141 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

   CREATE SEQUENCE  "NETCRACKER"."EMPLOYEE_SEQUENCE"  MINVALUE 1 MAXVALUE 1000 INCREMENT BY 1 START WITH 181 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

   CREATE SEQUENCE  "NETCRACKER"."POSITION_SEQUENCE"  MINVALUE 1 MAXVALUE 500 INCREMENT BY 1 START WITH 121 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
