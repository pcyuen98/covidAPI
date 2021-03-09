-- --------------------------------------------------------
-- Host:                         bayi
-- Server version:               PostgreSQL 10.14 on x86_64-redhat-linux-gnu, compiled by gcc (GCC) 8.3.1 20191121 (Red Hat 8.3.1-5), 64-bit
-- Server OS:                    
-- HeidiSQL Version:             10.1.0.5505
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping data for table public.country: 0 rows
/*!40000 ALTER TABLE "country" DISABLE KEYS */;
INSERT INTO "country" ("id", "code", "name") VALUES
	(1, E'1', E'MY');
/*!40000 ALTER TABLE "country" ENABLE KEYS */;


-- Dumping data for table public.state: 1 rows
/*!40000 ALTER TABLE "state" DISABLE KEYS */;
INSERT INTO "state" ("id", "code", "name", "fk_country_id") VALUES
	(1, E'1', E'KL', 1);
/*!40000 ALTER TABLE "state" ENABLE KEYS */;

-- Dumping data for table public.area: 0 rows
/*!40000 ALTER TABLE "area" DISABLE KEYS */;
INSERT INTO "area" ("id", "code", "name", "fk_state_id") VALUES
	(1, E'1', E'Lembah Pantai', 1);
/*!40000 ALTER TABLE "area" ENABLE KEYS */;


-- Dumping data for table public.trx_covid_cases: 1 rows
/*!40000 ALTER TABLE "trx_covid_cases" DISABLE KEYS */;
INSERT INTO "trx_covid_cases" ("id", "cases", "date", "fk_area_id") VALUES
	(1, 1, E'2020-11-28 21:15:27', 1);
/*!40000 ALTER TABLE "trx_covid_cases" ENABLE KEYS */;

INSERT INTO "trx_covid_cases_desc" ("id", "description") VALUES
	(1, E'1');
	
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
