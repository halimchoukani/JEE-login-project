package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; import java.util.List;

import Metier.SingletonConnection;
import Metier.Produit;
public  class  ProduitDaoImpl  implements  IProduitDao  { 
	@Override
	public Produit save(Produit p) {
		Connection conn=SingletonConnection.getConnection();
		try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO PRODUITS(NOM_PRODUIT,PRIX) VALUES(?,?)");
			ps.setString(1, p.getNomProduit()); ps.setDouble(2, p.getPrix()); ps.executeUpdate();
			
			
			PreparedStatement ps2= conn.prepareStatement ("SELECT MAX(ID_PRODUIT) as MAX_ID FROM PRODUITS");
			ResultSet rs =ps2.executeQuery();
			if (rs.next()) {
				p.setIdProduit(rs.getLong("MAX_ID"));
			}
			ps.close();
			ps2.close();
		
		}  catch  (SQLException  e)  { 
			e.printStackTrace();
		}
		return  p;
	}
	
	@Override
	public  List<Produit>  produitsParMC(String  mc)  { 
		List<Produit>  prods=  new  ArrayList<Produit>(); 
		Connection conn=SingletonConnection.getConnection(); 
		try {
			PreparedStatement ps= conn.prepareStatement("select * from PRODUITS where NOM_PRODUIT LIKE ?");
			ps.setString(1,  mc); 
			ResultSet rs = ps.executeQuery(); 
			while (rs.next()) {
				Produit p = new Produit(); p.setIdProduit(rs.getLong("ID_PRODUIT")); 
				p.setNomProduit(rs.getString("NOM_PRODUIT")); 
				p.setPrix(rs.getDouble("PRIX")); 
				prods.add(p);
			}
			ps.close();
			return prods;
		} catch  (SQLException  e)  { 
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public List<Produit> produits() {
        List<Produit> prods = new ArrayList<>();
        Connection conn = SingletonConnection.getConnection();

        if (conn == null) {
            System.out.println("DAO: Database connection is null");
            return prods; // Return empty list if connection is null
        }

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PRODUITS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setIdProduit(rs.getLong("ID_PRODUIT"));
                p.setNomProduit(rs.getString("NOM_PRODUIT"));
                p.setPrix(rs.getDouble("PRIX"));
                prods.add(p);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("DAO: Products retrieved: " + prods.size());
        return prods;
    }
	@Override
	public Produit getProduit(long id) {
	    Connection conn = SingletonConnection.getConnection(); 
	    try {
	        PreparedStatement ps = conn.prepareStatement("SELECT * FROM PRODUITS WHERE ID_PRODUIT=?");
	        ps.setLong(1, id);
	        ResultSet rs = ps.executeQuery(); 
	        if (rs.next()) {
	            Produit produit = new Produit();
	            produit.setIdProduit(rs.getLong("ID_PRODUIT")); 
	            produit.setNomProduit(rs.getString("NOM_PRODUIT")); 
	            produit.setPrix(rs.getDouble("PRIX")); 
	            ps.close();
	            return produit;
	        }
	    } catch(SQLException e) { 
	        e.printStackTrace();
	    }
	    return null;
	}

	
	@Override
	public Produit updateProduit(Produit p) {
		Connection conn=SingletonConnection.getConnection(); 
		try {
			PreparedStatement ps= conn.prepareStatement("update PRODUITS set NOM_PRODUIT=? PRIX=? where ID_PRODUIT=?");
			ps.setLong(3,p.getIdProduit());
			ps.setString(1,p.getNomProduit());
			ps.setDouble(2, p.getPrix());
			int rs = ps.executeUpdate(); 
			ps.close();
			return p;
		}catch(SQLException  e)  { 
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void deleteProduit(long id) {
	    Connection conn = SingletonConnection.getConnection(); 
	    try {
	    	PreparedStatement ps = conn.prepareStatement("DELETE FROM PRODUITS WHERE ID_PRODUIT = ?;");
	        ps.setLong(1,id);
	        ps.executeUpdate();
	        System.out.println("Deleted !!!");
	        ps.close();
	    } catch(SQLException e) { 
	        e.printStackTrace();
	    }
	}

}

