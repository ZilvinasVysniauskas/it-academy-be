INSERT INTO public.address (id, city, number, street) VALUES (1, 'Vilnius', 35, 'Konstitucijos pr.');
INSERT INTO public.building (id, name, address_id) VALUES (1, ' Building One', 1);

INSERT INTO public.floor (id, floor_number, building_id) VALUES (1, 5, 1);


INSERT INTO public.room (id, room_name, floor_id, room_deleted) VALUES (1, 'Room Nr1', 1, false );
INSERT INTO public.room (id, room_name, floor_id, room_deleted) VALUES (2, 'Room Nr2', 1, false );
INSERT INTO public.room (id, room_name, floor_id, room_deleted) VALUES (3, 'Room Nr3', 1, false );
INSERT INTO public.room (id, room_name, floor_id, room_deleted) VALUES (4, 'Room Nr4', 1, false );
INSERT INTO public.room (id, room_name, floor_id, room_deleted) VALUES (5, 'Room Nr5', 1, false );


INSERT INTO public.desk ( desk_name, is_available, room_id, desk_deleted) VALUES ( 2, true, 1, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (4, 3, true, 1, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (5, 4, true, 1, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (6, 1, true, 2, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (7, 2, true, 2, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (8, 3, true, 2, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (9, 4, true, 2, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (10, 1, true, 3, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (11, 2, true, 3, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (12, 3, true, 3, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (13, 4, true, 3, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (14, 1, true, 4, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (15, 2, true, 4, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (16, 3, true, 4, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (17, 4, true, 4, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (18, 1, true, 5, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (19, 2, true, 5, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (20, 3, true, 5, false);
INSERT INTO public.desk (id, desk_name, is_available, room_id, desk_deleted) VALUES (21, 4, true, 5, false);

INSERT INTO public.users_table (user_id, active, email, first_name, last_name, middle_name, password, role) VALUES (12345678, true, 'tests@corporate.com', 'fname', 'lname', 'mname', '$2a$10$YxVJofYGWZTCpguVvszODeALPSWlVNQ7fI4Z33dUxqordQX0ylHIC', 1);


