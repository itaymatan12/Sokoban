package entities;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/**
 *manager class that adding information and getting information from the database 
 *
 *
 */

public class DbManager {
	
	// Local Variables
	private static DbManager instance = new DbManager();
	private SessionFactory factory;

	// C'tor - Singleton Pattern
	public DbManager()
	{
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		Configuration configuration = new Configuration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

	// Singleton Pattern
	public static DbManager getInstance()
	{
		return instance;
	}

	// Function "addUser" = add new User to DataBase
	public void addUser(User p)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			session.save(p);
			tx.commit();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}
	}

	// Function "getAllUsers" = returns all Users rows from the DataBase
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers()
	{
		List<User> Users = null;
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			Users = session.createQuery("from Users").list();
			tx.commit();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return Users;
	}

	// Function "getLastRowUser" - returns the last User's table row
	public User getLastRowUser()
	{
		List<User> Users = getAllUsers();

		return Users.get(Users.size()-1);
	}

	// Function "addLevel" = add new Level to DataBase
	public void addLevel(HLevel l)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			session.save(l);
			tx.commit();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}
	}

	// Function "LevelisExist" - check if object already in the database
	@SuppressWarnings("deprecation")
	public boolean LevelisExist(String Id)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

		    Criteria criteria = session.createCriteria(HLevel.class);
		    criteria.add(Restrictions.eq("LevelName", Id));
		    criteria.setProjection(Projections.rowCount());
		    long count = (Long) criteria.uniqueResult();
		    tx.commit();

		   if(count != 0)
		       return true;

		   else
			   return false;
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}
		return true;
	}
	
	// Function "LevelisExist" - check if object already in the database
		@SuppressWarnings("deprecation")
		public boolean UserisExist(int Id)
		{
			Session session = null;
			Transaction tx = null;

			try
			{
				session = factory.openSession();
				tx = session.beginTransaction();

			    Criteria criteria = session.createCriteria(User.class);
			    criteria.add(Restrictions.eq("UserId", Id));
			    criteria.setProjection(Projections.rowCount());
			    long count = (Long) criteria.uniqueResult();
			    tx.commit();

			   if(count != 0)
			       return true;

			   else
				   return false;
			}

			catch (HibernateException ex)
			{
				if (tx != null)
					tx.rollback();
				System.out.println(ex.getMessage());
			}

			finally
			{
				if (session != null)
					session.close();
			}
			return true;
		}

	// Function "addRecord" = add User's record to DataBase
	public void addRecord(UserRecord pr)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(pr);
			tx.commit();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}
	}

	// Function "sortLevelScoresBySteps" - get from DB sorted UsersRecords
	@SuppressWarnings("unchecked")
	public List<UserRecord> sortLevelScoresBySteps(String LevelName)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			String hql = "FROM UserRecords  WHERE key.LevelName='"+LevelName+"' ORDER BY Steps ASC";
			Query<UserRecord> query = session.createQuery(hql);
			query.setMaxResults(10);

			return query.list();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRecord> sorUserScoresBySteps(String Userid)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			String hql = "FROM UserRecords  WHERE key.UserId='"+Userid+"' ORDER BY Steps ASC";
			Query<UserRecord> query = session.createQuery(hql);
			query.setMaxResults(10);

			return query.list();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRecord> sorUserScoresByTime(int Userid)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			String hql = "FROM UserRecords  WHERE key.UserId='"+Userid+"' ORDER BY Timer ASC";
			Query<UserRecord> query = session.createQuery(hql);
			query.setMaxResults(10);

			return query.list();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserRecord> sorUserScoresByLexicalOrder(int Userid)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			String hql = "FROM UserRecords  WHERE key.UserId='"+Userid+"' ORDER BY LevelName";
			Query<UserRecord> query = session.createQuery(hql);
			query.setMaxResults(10);

			return query.list();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}
	
	// Function "sortLevelScoresByTime" - get from DB sorted UsersRecords
	@SuppressWarnings("unchecked")
	public List<UserRecord> sortLevelScoresByTime(String LevelName)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			String hql = "FROM UserRecords  WHERE key.LevelName='"+LevelName+"' ORDER BY Timer ASC";
			Query<UserRecord> query = session.createQuery(hql);
			query.setMaxResults(10);

			return query.list();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}

	// Function "getUserScores" - return all User scores where UserId is shown
	@SuppressWarnings("unchecked")
	public List<UserRecord> getUserScores(String UserId)
	{
		Session session = null;
		Transaction tx = null;

		try
		{
			session = factory.openSession();
			tx = session.beginTransaction();

			String hql = "FROM UserRecords  WHERE key.UserId='"+UserId+"'";
			Query<UserRecord> query = session.createQuery(hql);

			return query.list();
		}

		catch (HibernateException ex)
		{
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}

		finally
		{
			if (session != null)
				session.close();
		}

		return null;
	}

	// Function "close" = close factory
	public void close()
	{
		factory.close();
	}

}
