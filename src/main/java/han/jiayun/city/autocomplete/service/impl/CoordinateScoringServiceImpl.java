package han.jiayun.city.autocomplete.service.impl;

import org.springframework.stereotype.Service;

import han.jiayun.city.autocomplete.model.Coordinate;
import han.jiayun.city.autocomplete.service.CoordinateScoringService;
import han.jiayun.city.autocomplete.util.GeoDistanceCalculator;

/**
 * 
 * @author Jiayun Han
 *
 */
@Service
public class CoordinateScoringServiceImpl implements CoordinateScoringService {

	@Override
	public double score(Coordinate coordinateA, Coordinate coordinateB) {
		double distance = GeoDistanceCalculator.haversineDistance(coordinateA, coordinateB);
		return toScore(distance);
	}

	private double toScore(double distance) {
		
		if(a_lessThan_b(distance, 10)) {
			return 1;
		} else if(a_lessThan_b(distance, 50)) {
			return 0.9;
		} else if(a_lessThan_b(distance, 100)) {
			return 0.85;
		} else if(a_lessThan_b(distance, 200)) {
			return 0.8;
		} else if(a_lessThan_b(distance, 300)) {
			return 0.75;
		} else if(a_lessThan_b(distance, 400)) {
			return 0.7;
		} else if(a_lessThan_b(distance, 500)) {
			return 0.65;
		} else if(a_lessThan_b(distance, 600)) {
			return 0.6;
		} else if(a_lessThan_b(distance, 700)) {
			return 0.55;
		} else if(a_lessThan_b(distance, 800)) {
			return 0.5;
		} else if(a_lessThan_b(distance, 900)) {
			return 0.45;
		} else if(a_lessThan_b(distance, 1000)) {
			return 0.4;
		} else if(a_lessThan_b(distance, 1500)) {
			return 0.35;
		} else if(a_lessThan_b(distance, 2000)) {
			return 0.3;
		} else if(a_lessThan_b(distance, 2500)) {
			return 0.25;
		} else if(a_lessThan_b(distance, 3000)) {
			return 0.2;
		} else if(a_lessThan_b(distance, 3500)) {
			return 0.15;
		} else if(a_lessThan_b(distance, 4000)) {
			return 0.1;
		}
		return 0.05;
	}
	
	private static boolean a_lessThan_b(double a, double b) {
		double result = Double.compare(a, b);
		return result < 1;
	}
}
