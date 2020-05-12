import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "LewanSoulHorn"
	if(args==null)
		args=["round"]
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def hornDiameterValue = measurments.hornDiameter
	def priceValue = measurments.price
	def massCentroidYValue = measurments.massCentroidY
	def massCentroidXValue = measurments.massCentroidX
	def mountPlateToHornTopValue = measurments.mountPlateToHornTop
	def massCentroidZValue = measurments.massCentroidZ
	def sourceValue = measurments.source
	def massKgValue = measurments.massKg
	println "Measurment hornDiameterValue =  "+hornDiameterValue
	println "Measurment priceValue =  "+priceValue
	println "Measurment massCentroidYValue =  "+massCentroidYValue
	println "Measurment massCentroidXValue =  "+massCentroidXValue
	println "Measurment mountPlateToHornTopValue =  "+mountPlateToHornTopValue
	println "Measurment massCentroidZValue =  "+massCentroidZValue
	println "Measurment sourceValue =  "+sourceValue
	println "Measurment massKgValue =  "+massKgValue
	// Stub of a CAD object
	CSG part = new Cube().toCSG()
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 