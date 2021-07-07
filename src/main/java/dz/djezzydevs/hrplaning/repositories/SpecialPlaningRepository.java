package dz.djezzydevs.hrplaning.repositories;

import dz.djezzydevs.hrplaning.dto.EmployeePlaned;
import dz.djezzydevs.hrplaning.dto.EmployeeToPlan;
import dz.djezzydevs.hrplaning.dto.Event;
import dz.djezzydevs.hrplaning.entities.UserAdEntity;
import dz.djezzydevs.hrplaning.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class SpecialPlaningRepository {


    private String fields ="employee_id,email,win_session,employee_id || ' : ' || full_name as full_name,department,job,location,organisation,region,manager";
    @Autowired
    UserAdRepository userAdRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;



    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<EmployeeToPlan> findAllByManager(String win_session) {
//        return jdbcTemplate.query("select employee_id,email from employee_ad where win_session =?",
//                new BeanPropertyRowMapper<EmployeeToPlan>(EmployeeToPlan.class));


        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("win_session", win_session.toLowerCase());
//        return namedParameterJdbcTemplate.queryForObject(
//                "select employee_id,email from employee_ad WHERE lower(win_session) = :win_session", namedParameters, List.class);
            return namedParameterJdbcTemplate.query("select  "+this.fields+"  from employee_ad WHERE lower(win_session) = :win_session",
                    namedParameters, new BeanPropertyRowMapper<EmployeeToPlan>(EmployeeToPlan.class));


    }

    public List<EmployeeToPlan> findAllByManager1(String win_session) {

       Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();

       SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("manager", id);
        return namedParameterJdbcTemplate.query("select  "+this.fields+"  from employee_view_advanced WHERE " +
                        "(manager =:manager  or employee_id =:manager or managerplan =:manager)",
                namedParameters, new BeanPropertyRowMapper<EmployeeToPlan>(EmployeeToPlan.class));


    }

    public List<EmployeePlaned> findAllPlanedByManager(String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("manager", id);
        return namedParameterJdbcTemplate.query("select a.employee_id, b.employee_session   , a.full_name  , b.plan_date from public.employee_view_advanced a, planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "    b.plan_date  >= now() - interval '30 day' " +
                        "    and   (a.manager =:manager  or a.employee_id =:manager or a.managerplan =:manager)",
                namedParameters, new BeanPropertyRowMapper<EmployeePlaned>(EmployeePlaned.class));


    }

    public List<Event> findAllPlanedByManager1(String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("manager", id);
        return namedParameterJdbcTemplate.query("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,  b.plan_date as date from public.employee_view_advanced a, planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "    b.plan_date  >= now() - interval '30 day' " +
                        "    and   (a.manager =:manager  or a.employee_id =:manager or a.managerplan =:manager)",
                namedParameters, new BeanPropertyRowMapper<Event>(Event.class));


    }

    public List<String> findOrganisations(String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.query("select  a.organisation  as organisation  from public.employee_view_advanced a where "+
                        " a.employee_id =:id    or    (a.manager =:id   or a.managerplan =:id)",
                namedParameters, new BeanPropertyRowMapper<>(String.class));

    }


    public List<Event> findAllPlanedByUser(String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("emp", id);
        return namedParameterJdbcTemplate.query("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,  b.plan_date as date from public.employee_view_advanced a, planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "    b.plan_date  >= now() - interval '90 day' " +
                        "    and   (  a.employee_id =:emp )",
                namedParameters, new BeanPropertyRowMapper<Event>(Event.class));




    }

    public List<Event> findAllPlanedByDirector(String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("emp", id);
        return namedParameterJdbcTemplate.query("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,  b.plan_date as date ,a.organisation," +
                        "    a.department" +
                        "    from public.employee_view_advanced a, planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and" +
                        "    b.plan_date  >= now() - interval '90 day'" +
                        "    and  a.organisation  in (" +
                        "     select  organisation    from public.employee_view_advanced  where\n" +
                        "      a.employee_id =:emp" +
                        "            or    manager =:emp   or managerplan =:emp " +
                        "    )",
                namedParameters, new BeanPropertyRowMapper<Event>(Event.class));




    }


    public List<Event> findAllPlanedByOfficer(String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();
        String department =userRepository.findById(id).get().getDepartment();

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("department", department);
        return namedParameterJdbcTemplate.query("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,  b.plan_date as date from public.employee_view_advanced a, planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "    b.plan_date  >= now() - interval '90 day' " +
                        "    and   (a.department =:department)",
                namedParameters, new BeanPropertyRowMapper<Event>(Event.class));


    }

//    public List<Event> findAllPlanedByHrBp(String win_session,String sector) {
//        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("sector", sector);
//        return namedParameterJdbcTemplate.query("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,  b.plan_date as date from public.employee_view_advanced a, planing  b" +
//                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
//                        "    b.plan_date  >= now() - interval '90 day' " +
//                        "    and   (a.sector =:sector)",
//                namedParameters, new BeanPropertyRowMapper<Event>(Event.class));
//
//    }


    public List<Event> findAllPlanedByHrBp(String win_session,List<String> sector) {

        //List <String>sectors =new ArrayList<String>();

        System.out.println(sector);
       // sectors.add("Technology");
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("sector", sector);
        return namedParameterJdbcTemplate.query("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,  b.plan_date as date from public.employee_view_advanced a, planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "    b.plan_date  >= now() - interval '90 day' " +
                        "    and   (a.sector in (:sector))",
                namedParameters, new BeanPropertyRowMapper<Event>(Event.class));

    }

    public List<String> getEmails( String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("manager", id);
        return namedParameterJdbcTemplate.queryForList("select  distinct(a.email)  from public.employee_view_advanced a ,planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "     b.plan_date  >= now() - interval '30 day' " +
                        "    and   (a.manager =:manager  or  a.managerplan =:manager)",
                namedParameters, String.class);
    }


    public List<Event> findAllPlanedByList(List<String> emails) {

        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("emails", emails);
        return namedParameterJdbcTemplate.query("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,to_char(b.plan_date, 'DD/MM/YYYY')   as date from public.employee_view_advanced a, planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "     b.insert_date  = CURRENT_DATE  " +
                        "    and   (a.email in (:emails)) ORDER BY b.plan_date asc ",
                namedParameters, new BeanPropertyRowMapper<Event>(Event.class));

      //  to_char("date", 'DD/MM/YYYY')
//          Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();
//        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("manager", id);
//        return namedParameterJdbcTemplate.queryForList("select  b.employee_session  as id ,a.employee_id || ' : ' || a.full_name as title  ,  b.plan_date as date  from public.employee_view_advanced a ,planing  b" +
//                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
//                        "     b.insert_date  = CURRENT_DATE  " +
//                        "    and   (a.manager =:manager  or  a.managerplan =:manager)",
//                namedParameters, String.class);




    }





    public List<String> getTodayEmails( String win_session) {

        Long  id =userAdRepository.findByWinSessionIgnoreCase(win_session).getEmployeeId();
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("manager", id);
        return namedParameterJdbcTemplate.queryForList("select  distinct(a.email)  from public.employee_view_advanced a ,planing  b" +
                        "    where lower(a.win_session) =lower(b.employee_session)  and " +
                        "     b.insert_date  = CURRENT_DATE  " +
                        "    and   (a.manager =:manager  or  a.managerplan =:manager)",
                namedParameters, String.class);
    }

  public  void  updateUserSitePlan (String win_session,String manager,Date startDate,Date enddate,String site,String etage,String region) {
       String SQL = "update planing set site = ?  ,etage= ? , inserted_by =? where lower(employee_session)=lower(?) and  plan_date between  ? and ?";
       jdbcTemplate.update(SQL, site, etage,manager,win_session,startDate,enddate);

   }

}
