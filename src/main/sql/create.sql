-- create database comic_box character set utf8 collate utf8_bin;
use comic_box;

create table appearance (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    classification varchar(32) not null,
    constraint pk_appearance primary key (id),
    unique key uk_appearance_classification (classification)
) ENGINE=InnoDB;

create table category (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(32) not null,
    constraint pk_category primary key (id),
    unique key uk_category_name (name)
) ENGINE=InnoDB;

create table country (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(32) not null,
    constraint pk_country primary key (id),
    unique key uk_country_name (name)
) ENGINE=InnoDB;

create table distribution (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(32) not null,
    constraint pk_distribution primary key (id),
    unique key uk_distribution_name (name)
) ENGINE=InnoDB;

create table duration (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(32) not null,
    constraint pk_duration primary key (id),
    unique key uk_duration_name (name)
) ENGINE=InnoDB;

create table format (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(32) not null,
    constraint pk_format primary key (id),
    unique key uk_format_name (name)
) ENGINE=InnoDB;

create table grade (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    code varchar(5) not null,
    name varchar(20) not null,
    scale float(3,1) not null,
    constraint pk_grade primary key (id),
    unique key uk_grade_code (code),
    unique key uk_grade_name (name),
    unique key uk_grade_scale (scale)
) ENGINE=InnoDB;

create table image (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    filename varchar(128) not null,
    constraint pk_image primary key (id),
    unique key uk_image_filename (filename)
) ENGINE=InnoDB;

create table location (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(32) not null,
    username varchar(32) not null,
    constraint pk_location primary key (id),
    unique key uk_location_name_username (name, username)
) ENGINE=InnoDB;

create table person (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    first_name varchar(32) not null,
    last_name varchar(32) not null,
    constraint pk_person primary key (id),
    unique key uk_person_first_name_last_name (first_name, last_name)
) ENGINE=InnoDB;

create table profession (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(32) not null,
    constraint pk_profession primary key (id),
    unique key uk_profession_name (name)
) ENGINE=InnoDB;

create table creator (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    person_id binary(16) not null,
    person_id_text varchar(32) generated always as (hex(person_id)) virtual,
    profession_id binary(16) not null,
    profession_id_text varchar(32) generated always as (hex(profession_id)) virtual,
    constraint pk_creator primary key (id),
    constraint fk_creator_person_id foreign key (person_id) references person (id),
    constraint fk_creator_profession_id foreign key (profession_id) references profession (id),
    unique key uk_creator_person_id_profession_id (person_id, profession_id)
) ENGINE=InnoDB;

create table publisher (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    country_id binary(16) not null,
    country_id_text varchar(32) generated always as (hex(country_id)) virtual,
    image_id binary(16),
    image_id_text varchar(32) generated always as (hex(image_id)) virtual,
    name varchar(64) not null,
    constraint pk_publisher primary key (id),
    constraint fk_publisher_country_id foreign key (country_id) references country (id),
    constraint fk_publisher_image_id foreign key (image_id) references image (id),
    unique key uk_publisher_country_id_name (country_id, name)
) ENGINE=InnoDB;

create table imprint (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    name varchar(64) not null,
    publisher_id binary(16) not null,
    constraint pk_imprint primary key (id),
    constraint fk_imprint_publisher foreign key (publisher_id) references publisher (id),
    unique key uk_imprint_name_publisher_id (name, publisher_id)
) ENGINE=InnoDB;

create table title (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    duration_id binary(16) not null,
    duration_id_text varchar(32) generated always as (hex(duration_id)) virtual,
    format_id binary(16) not null,
    format_id_text varchar(32) generated always as (hex(format_id)) virtual,
    image_id binary(16),
    image_id_text varchar(32) generated always as (hex(image_id)) virtual,
    name varchar(128) not null,
    publish_end_date date,
    publish_start_date date,
    volume int(2) unsigned not null,
    constraint pk_title primary key (id),
    constraint fk_title_duration_id foreign key (duration_id) references duration (id),
    constraint fk_title_format_id foreign key (format_id) references format (id),
    constraint fk_title_image_id foreign key (image_id) references image (id),
    unique key uk_title_name_volume (name, volume)
) ENGINE=InnoDB;

create table title_category (
    category_id binary(16) not null,
    category_id_text varchar(32) generated always as (hex(category_id)) virtual,
    title_id binary(16) not null,
    title_id_text varchar(32) generated always as (hex(title_id)) virtual,
    constraint fk_title_category_category_id foreign key (category_id) references category (id),
    constraint fk_title_category_title_id foreign key (title_id) references title (id)
) ENGINE=InnoDB;

create table title_imprint (
    imprint_id binary(16) not null,
    imprint_id_text varchar(32) generated always as (hex(imprint_id)) virtual,
    title_id binary(16) not null,
    title_id_text varchar(32) generated always as (hex(title_id)) virtual,
    constraint fk_title_imprint_imprint_id foreign key (imprint_id) references imprint (id),
    constraint fk_title_imprint_title_id foreign key (title_id) references title (id)
) ENGINE=InnoDB;

create table title_publisher (
    publisher_id binary(16) not null,
    publisher_id_text varchar(32) generated always as (hex(publisher_id)) virtual,
    title_id binary(16) not null,
    title_id_text varchar(32) generated always as (hex(title_id)) virtual,
    constraint fk_title_publisher_publisher_id foreign key (publisher_id) references publisher (id),
    constraint fk_title_publisher_title_id foreign key (title_id) references title (id)
) ENGINE=InnoDB;

create table comic (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    cover_price float(5,2),
    description varchar(256),
    distribution_id binary(16) not null,
    distribution_id_text varchar(32) generated always as (hex(distribution_id)) virtual,
    identifier varchar(16) not null,
    image_id binary(16),
    image_id_text varchar(32) generated always as (hex(image_id)) virtual,
    print int(2) unsigned not null,
    publish_date date,
    title_id binary(16) not null,
    title_id_text varchar(32) generated always as (hex(title_id)) virtual,
    constraint pk_comic primary key (id),
    constraint fk_comic_distribution_id foreign key (distribution_id) references distribution (id),
    constraint fk_comic_image_id foreign key (image_id) references image (id),
    constraint fk_comic_title_id foreign key (title_id) references title (id),
    unique key uk_comic_identifier_title_id (identifier, title_id)
) ENGINE=InnoDB;

create table comic_creator (
    comic_id binary(16) not null,
    comic_id_text varchar(32) generated always as (hex(comic_id)) virtual,
    creator_id binary(16) not null,
    creator_id_text varchar(32) generated always as (hex(creator_id)) virtual,
    constraint fk_comic_creator_comic_id foreign key (comic_id) references comic (id),
    constraint fk_comic_creator_creator_id foreign key (creator_id) references creator (id)
) ENGINE=InnoDB;

create table actor (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    image_id binary(16),
    image_id_text varchar(32) generated always as (hex(image_id)) virtual,
    iteration int(2) unsigned not null,
    name varchar(32) not null,
    constraint pk_actor primary key (id),
    constraint fk_actor_image_id foreign key (image_id) references image (id),
    unique key uk_actor_iteration_name (iteration, name)
) ENGINE=InnoDB;

create table comic_actor (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    actor_id binary(16) not null,
    actor_id_text varchar(32) generated always as (hex(actor_id)) virtual,
    appearance_id binary(16) not null,
    appearance_id_text varchar(32) generated always as (hex(appearance_id)) virtual,
    comic_id binary(16) not null,
    comic_id_text varchar(32) generated always as (hex(comic_id)) virtual,
    constraint pk_comic_actor primary key (id),
    constraint fk_comic_actor_actor_id foreign key (actor_id) references actor (id),
    constraint fk_comic_actor_appearance_id foreign key (appearance_id) references appearance (id),
    constraint fk_comic_actor_comic_id foreign key (comic_id) references comic (id),
    unique key uk_comic_actor_actor_id_appearance_id_comic_id (actor_id, appearance_id, comic_id)
) ENGINE=InnoDB;

create table inventory (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    comic_id binary(16) not null,
    comic_id_text varchar(32) generated always as (hex(comic_id)) virtual,
    grade_id binary(16) not null,
    grade_id_text varchar(32) generated always as (hex(grade_id)) virtual,
    location_id binary(16) not null,
    location_id_text varchar(32) generated always as (hex(location_id)) virtual,
    purchase_price float(5,2),
    quantity int(3) unsigned not null,
    username varchar(32) not null,
    constraint pk_inventory primary key (id),
    constraint fk_inventory_comic_id foreign key (comic_id) references comic (id),
    constraint fk_inventory_grade_id foreign key (grade_id) references grade (id),
    constraint fk_inventory_location_id foreign key (location_id) references location (id),
    unique key uk_inventory_comic_id_grade_id_location_id_username (comic_id, grade_id, location_id, username)
) ENGINE=InnoDB;

create table want_list (
    id binary(16) not null,
    id_text varchar(32) generated always as (hex(id)) virtual,
    created_time datetime(6) not null,
    updated_time datetime(6) not null,
    version bigint unsigned not null default 0,
    comic_id binary(16) not null,
    comic_id_text varchar(32) generated always as (hex(comic_id)) virtual,
    username varchar(32) not null,
    constraint pk_want_list primary key (id),
    constraint fk_want_list_comic_id foreign key (comic_id) references comic (id),
    unique key uk_want_list_comic_id_username (comic_id, username)
) ENGINE=InnoDB;

insert into appearance (id, created_time, updated_time, classification) values (unhex(replace(uuid(),'-','')), now(), now(), 'first appearance');
insert into appearance (id, created_time, updated_time, classification) values (unhex(replace(uuid(),'-','')), now(), now(), 'mention');
insert into appearance (id, created_time, updated_time, classification) values (unhex(replace(uuid(),'-','')), now(), now(), 'flashback');

insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'GM', 'Gem Mint', 10.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'MT', 'Mint', 9.9);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'NM/MT', 'Near Mint/Mint', 9.8);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'NM+', 'Near Mint+', 9.6);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'NM', 'Near Mint', 9.4);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'NM-', 'Near Mint-', 9.2);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VF/NM', 'Very Fine/Near Mint', 9.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VF+', 'Very Fine+', 8.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VF', 'Very Fine', 8.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VF-', 'Very Fine-', 7.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'FN/VF', 'Fine/Very Fine', 7.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'FN+', 'Fine+', 6.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'FN', 'Fine', 6.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'FN-', 'Fine-', 5.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VG/FN', 'Very Good/Fine', 5.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VG+', 'Very Good+', 4.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VG', 'Very Good', 4.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'VG-', 'Very Good-', 3.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'GD/VG', 'Good/Very Good', 3.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'GD+', 'Good+', 2.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'GD', 'Good', 2.0);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'GD-', 'Good-', 1.8);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'FR/GD', 'Fair/Good', 1.5);
insert into grade (id, created_time, updated_time, code, name, scale) values (unhex(replace(uuid(),'-','')), now(), now(), 'FR', 'Fair', 1.0);
