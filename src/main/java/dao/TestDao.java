package dao;

import java.util.List;
import Metier.Produit;

public class TestDao {

	public  static  void  main(String[]  args)  { 
		ProduitDaoImpl  pdao=  new  ProduitDaoImpl();
		List<Produit>  prods  =pdao.produits();
		for (Produit p : prods) System.out.println(p.toString());
	}

}
