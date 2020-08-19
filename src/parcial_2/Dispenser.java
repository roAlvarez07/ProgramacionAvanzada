/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcial_2;

/**
 *
 * @author MatiRo
 */
public class Dispenser {
	
	public static final Double MONTO_BILLETE = 20.0;
	public static final Integer BILLETE_INICIALES = 500;

	private Double saldo;
	
	public Dispenser() {
	}
	
	public Dispenser(Integer cantidad) {
		this.saldo = BILLETE_INICIALES * MONTO_BILLETE;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void restar(Double monto) {
		saldo = saldo - monto;
	}
	
	public boolean haySaldoDisponible(Double monto) {
		return (saldo >= monto);
	}
	
	@Override
	public String toString() {
		return "Dispenser [saldo=" + saldo + "]";
	}
	
	
	
	
	

}
