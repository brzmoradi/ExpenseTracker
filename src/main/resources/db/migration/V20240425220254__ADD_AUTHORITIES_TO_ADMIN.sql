set @ADMIN_ID := NULL;
select id into @ADMIN_ID from user_ where username='admin';

insert into user_role(user_id,role_id) select @ADMIN_ID,a.id from role a ;