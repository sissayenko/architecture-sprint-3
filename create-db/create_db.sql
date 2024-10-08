--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2024-10-04 20:51:30

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16399)
-- Name: device_management; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA device_management;


ALTER SCHEMA device_management OWNER TO postgres;

--
-- TOC entry 7 (class 2615 OID 24576)
-- Name: profile_management; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA profile_management;


ALTER SCHEMA profile_management OWNER TO postgres;

--
-- TOC entry 9 (class 2615 OID 24791)
-- Name: telemetry; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA telemetry;


ALTER SCHEMA telemetry OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 16401)
-- Name: device; Type: TABLE; Schema: device_management; Owner: postgres
--

CREATE TABLE device_management.device (
    id bigint NOT NULL,
    house_id character varying NOT NULL,
    r_device_type_code character varying NOT NULL,
    name character varying NOT NULL,
    is_enabled boolean DEFAULT true NOT NULL,
    update_timestamp timestamp without time zone DEFAULT now() NOT NULL,
    external_id character varying
);


ALTER TABLE device_management.device OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16400)
-- Name: device_id_seq; Type: SEQUENCE; Schema: device_management; Owner: postgres
--

CREATE SEQUENCE device_management.device_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE device_management.device_id_seq OWNER TO postgres;

--
-- TOC entry 3398 (class 0 OID 0)
-- Dependencies: 218
-- Name: device_id_seq; Type: SEQUENCE OWNED BY; Schema: device_management; Owner: postgres
--

ALTER SEQUENCE device_management.device_id_seq OWNED BY device_management.device.id;


--
-- TOC entry 221 (class 1259 OID 16412)
-- Name: r_device_type; Type: TABLE; Schema: device_management; Owner: postgres
--

CREATE TABLE device_management.r_device_type (
    id bigint NOT NULL,
    code character varying NOT NULL,
    is_enabled boolean DEFAULT true NOT NULL
);


ALTER TABLE device_management.r_device_type OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16411)
-- Name: r_device_type_id_seq; Type: SEQUENCE; Schema: device_management; Owner: postgres
--

CREATE SEQUENCE device_management.r_device_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE device_management.r_device_type_id_seq OWNER TO postgres;

--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 220
-- Name: r_device_type_id_seq; Type: SEQUENCE OWNED BY; Schema: device_management; Owner: postgres
--

ALTER SEQUENCE device_management.r_device_type_id_seq OWNED BY device_management.r_device_type.id;


--
-- TOC entry 223 (class 1259 OID 24578)
-- Name: house; Type: TABLE; Schema: profile_management; Owner: postgres
--

CREATE TABLE profile_management.house (
    id bigint NOT NULL,
    householder_id bigint NOT NULL,
    address character varying NOT NULL,
    is_enabled boolean DEFAULT true NOT NULL,
    update_timestamp timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE profile_management.house OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24589)
-- Name: householder; Type: TABLE; Schema: profile_management; Owner: postgres
--

CREATE TABLE profile_management.householder (
    id bigint NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    is_enabled boolean DEFAULT true NOT NULL,
    update_timestamp timestamp without time zone DEFAULT now() NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE profile_management.householder OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 24588)
-- Name: householder_id_seq; Type: SEQUENCE; Schema: profile_management; Owner: postgres
--

CREATE SEQUENCE profile_management.householder_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profile_management.householder_id_seq OWNER TO postgres;

--
-- TOC entry 3400 (class 0 OID 0)
-- Dependencies: 224
-- Name: householder_id_seq; Type: SEQUENCE OWNED BY; Schema: profile_management; Owner: postgres
--

ALTER SEQUENCE profile_management.householder_id_seq OWNED BY profile_management.householder.id;


--
-- TOC entry 222 (class 1259 OID 24577)
-- Name: houses_id_seq; Type: SEQUENCE; Schema: profile_management; Owner: postgres
--

CREATE SEQUENCE profile_management.houses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE profile_management.houses_id_seq OWNER TO postgres;

--
-- TOC entry 3401 (class 0 OID 0)
-- Dependencies: 222
-- Name: houses_id_seq; Type: SEQUENCE OWNED BY; Schema: profile_management; Owner: postgres
--

ALTER SEQUENCE profile_management.houses_id_seq OWNED BY profile_management.house.id;


--
-- TOC entry 231 (class 1259 OID 24803)
-- Name: queue; Type: TABLE; Schema: telemetry; Owner: postgres
--

CREATE TABLE telemetry.queue (
    id bigint NOT NULL,
    device_id character varying,
    device_type character varying
);


ALTER TABLE telemetry.queue OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 24802)
-- Name: queue_id_seq; Type: SEQUENCE; Schema: telemetry; Owner: postgres
--

CREATE SEQUENCE telemetry.queue_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE telemetry.queue_id_seq OWNER TO postgres;

--
-- TOC entry 3402 (class 0 OID 0)
-- Dependencies: 230
-- Name: queue_id_seq; Type: SEQUENCE OWNED BY; Schema: telemetry; Owner: postgres
--

ALTER SEQUENCE telemetry.queue_id_seq OWNED BY telemetry.queue.id;


--
-- TOC entry 233 (class 1259 OID 24814)
-- Name: telemetry; Type: TABLE; Schema: telemetry; Owner: postgres
--

CREATE TABLE telemetry.telemetry (
    id bigint NOT NULL,
    device_id character varying,
    telemetry_timestamp timestamp without time zone DEFAULT now(),
    value character varying,
    unit character varying,
    sensor character varying
);


ALTER TABLE telemetry.telemetry OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 24813)
-- Name: telemetry_id_seq; Type: SEQUENCE; Schema: telemetry; Owner: postgres
--

CREATE SEQUENCE telemetry.telemetry_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE telemetry.telemetry_id_seq OWNER TO postgres;

--
-- TOC entry 3403 (class 0 OID 0)
-- Dependencies: 232
-- Name: telemetry_id_seq; Type: SEQUENCE OWNED BY; Schema: telemetry; Owner: postgres
--

ALTER SEQUENCE telemetry.telemetry_id_seq OWNED BY telemetry.telemetry.id;


--
-- TOC entry 3210 (class 2604 OID 16404)
-- Name: device id; Type: DEFAULT; Schema: device_management; Owner: postgres
--

ALTER TABLE ONLY device_management.device ALTER COLUMN id SET DEFAULT nextval('device_management.device_id_seq'::regclass);


--
-- TOC entry 3213 (class 2604 OID 16415)
-- Name: r_device_type id; Type: DEFAULT; Schema: device_management; Owner: postgres
--

ALTER TABLE ONLY device_management.r_device_type ALTER COLUMN id SET DEFAULT nextval('device_management.r_device_type_id_seq'::regclass);


--
-- TOC entry 3215 (class 2604 OID 24581)
-- Name: house id; Type: DEFAULT; Schema: profile_management; Owner: postgres
--

ALTER TABLE ONLY profile_management.house ALTER COLUMN id SET DEFAULT nextval('profile_management.houses_id_seq'::regclass);


--
-- TOC entry 3218 (class 2604 OID 24592)
-- Name: householder id; Type: DEFAULT; Schema: profile_management; Owner: postgres
--

ALTER TABLE ONLY profile_management.householder ALTER COLUMN id SET DEFAULT nextval('profile_management.householder_id_seq'::regclass);


--
-- TOC entry 3221 (class 2604 OID 24806)
-- Name: queue id; Type: DEFAULT; Schema: telemetry; Owner: postgres
--

ALTER TABLE ONLY telemetry.queue ALTER COLUMN id SET DEFAULT nextval('telemetry.queue_id_seq'::regclass);


--
-- TOC entry 3222 (class 2604 OID 24817)
-- Name: telemetry id; Type: DEFAULT; Schema: telemetry; Owner: postgres
--

ALTER TABLE ONLY telemetry.telemetry ALTER COLUMN id SET DEFAULT nextval('telemetry.telemetry_id_seq'::regclass);


--
-- TOC entry 3384 (class 0 OID 16412)
-- Dependencies: 221
-- Data for Name: r_device_type; Type: TABLE DATA; Schema: device_management; Owner: postgres
--

INSERT INTO device_management.r_device_type VALUES (1, 'CCTV', true);
INSERT INTO device_management.r_device_type VALUES (2, 'HEATER', true);
INSERT INTO device_management.r_device_type VALUES (3, 'LIGHT', true);
INSERT INTO device_management.r_device_type VALUES (4, 'GATE', true);


--
-- TOC entry 3404 (class 0 OID 0)
-- Dependencies: 218
-- Name: device_id_seq; Type: SEQUENCE SET; Schema: device_management; Owner: postgres
--

SELECT pg_catalog.setval('device_management.device_id_seq', 14, true);


--
-- TOC entry 3405 (class 0 OID 0)
-- Dependencies: 220
-- Name: r_device_type_id_seq; Type: SEQUENCE SET; Schema: device_management; Owner: postgres
--

SELECT pg_catalog.setval('device_management.r_device_type_id_seq', 4, true);


--
-- TOC entry 3406 (class 0 OID 0)
-- Dependencies: 224
-- Name: householder_id_seq; Type: SEQUENCE SET; Schema: profile_management; Owner: postgres
--

SELECT pg_catalog.setval('profile_management.householder_id_seq', 6, true);


--
-- TOC entry 3407 (class 0 OID 0)
-- Dependencies: 222
-- Name: houses_id_seq; Type: SEQUENCE SET; Schema: profile_management; Owner: postgres
--

SELECT pg_catalog.setval('profile_management.houses_id_seq', 11, true);


--
-- TOC entry 3408 (class 0 OID 0)
-- Dependencies: 230
-- Name: queue_id_seq; Type: SEQUENCE SET; Schema: telemetry; Owner: postgres
--

SELECT pg_catalog.setval('telemetry.queue_id_seq', 68, true);


--
-- TOC entry 3409 (class 0 OID 0)
-- Dependencies: 232
-- Name: telemetry_id_seq; Type: SEQUENCE SET; Schema: telemetry; Owner: postgres
--

SELECT pg_catalog.setval('telemetry.telemetry_id_seq', 15, true);


--
-- TOC entry 3225 (class 2606 OID 16410)
-- Name: device device_pk; Type: CONSTRAINT; Schema: device_management; Owner: postgres
--

ALTER TABLE ONLY device_management.device
    ADD CONSTRAINT device_pk PRIMARY KEY (id);


--
-- TOC entry 3227 (class 2606 OID 16420)
-- Name: r_device_type r_device_type_pk; Type: CONSTRAINT; Schema: device_management; Owner: postgres
--

ALTER TABLE ONLY device_management.r_device_type
    ADD CONSTRAINT r_device_type_pk PRIMARY KEY (id);


--
-- TOC entry 3229 (class 2606 OID 16422)
-- Name: r_device_type r_device_type_unique; Type: CONSTRAINT; Schema: device_management; Owner: postgres
--

ALTER TABLE ONLY device_management.r_device_type
    ADD CONSTRAINT r_device_type_unique UNIQUE (code);


--
-- TOC entry 3233 (class 2606 OID 24598)
-- Name: householder householder_pk; Type: CONSTRAINT; Schema: profile_management; Owner: postgres
--

ALTER TABLE ONLY profile_management.householder
    ADD CONSTRAINT householder_pk PRIMARY KEY (id);


--
-- TOC entry 3231 (class 2606 OID 24587)
-- Name: house houses_pk; Type: CONSTRAINT; Schema: profile_management; Owner: postgres
--

ALTER TABLE ONLY profile_management.house
    ADD CONSTRAINT houses_pk PRIMARY KEY (id);


--
-- TOC entry 3235 (class 2606 OID 24811)
-- Name: queue queue_pk; Type: CONSTRAINT; Schema: telemetry; Owner: postgres
--

ALTER TABLE ONLY telemetry.queue
    ADD CONSTRAINT queue_pk PRIMARY KEY (id);


--
-- TOC entry 3237 (class 2606 OID 24822)
-- Name: telemetry telemetry_pk; Type: CONSTRAINT; Schema: telemetry; Owner: postgres
--

ALTER TABLE ONLY telemetry.telemetry
    ADD CONSTRAINT telemetry_pk PRIMARY KEY (id);


--
-- TOC entry 3238 (class 2606 OID 16423)
-- Name: device device_r_device_type_fk; Type: FK CONSTRAINT; Schema: device_management; Owner: postgres
--

ALTER TABLE ONLY device_management.device
    ADD CONSTRAINT device_r_device_type_fk FOREIGN KEY (r_device_type_code) REFERENCES device_management.r_device_type(code);


-- Completed on 2024-10-04 20:51:30

--
-- PostgreSQL database dump complete
--

