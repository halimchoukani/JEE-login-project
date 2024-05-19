package dao;

import java.util.List;

import Metier.Produit;

public interface IProduitDao {
	public Produit save(Produit p);
	public  List<Produit>  produitsParMC(String  mc);
	public Produit getProduit(long id); 
	public  Produit  updateProduit(Produit  p); 
	public  void  deleteProduit(long  id); 
	public List<Produit>  produits();
}
