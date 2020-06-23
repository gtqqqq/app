/*
 Navicat PostgreSQL Data Transfer

 Source Server         : localhost_5432
 Source Server Type    : PostgreSQL
 Source Server Version : 100008
 Source Host           : localhost:5432
 Source Catalog        : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100008
 File Encoding         : 65001

 Date: 19/05/2019 20:20:48
*/


-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS "public"."test";
CREATE TABLE "public"."test" (
  "id" inet NOT NULL,
  "id2" varchar(255) COLLATE "pg_catalog"."default",
  "i3" bool,
  "i5" tsquery,
  "t4" box
)
;
COMMENT ON COLUMN "public"."test"."id" IS 'sadf';
COMMENT ON COLUMN "public"."test"."id2" IS 'sdf';
COMMENT ON COLUMN "public"."test"."i3" IS 'sdf';
COMMENT ON COLUMN "public"."test"."i5" IS 'sdf';

-- ----------------------------
-- Primary Key structure for table test
-- ----------------------------
ALTER TABLE "public"."test" ADD CONSTRAINT "test_pkey" PRIMARY KEY ("id");
