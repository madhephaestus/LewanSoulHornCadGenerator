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
	def caseHoleDiameterValue = measurments.caseHoleDiameter

	// Stub of a CAD object
	CSG part = new Cylinder(hornDiameterValue/2,mountPlateToHornTopValue).toCSG()
	def bodyScrew = new Cylinder(	caseHoleDiameterValue/2,
									measurments.get("caseScrewKeepawayLength")).toCSG()
						.union( new Cylinder(	measurments.get("caseScrewHeadDiameter")/2,
									10).toCSG()
									.movez(measurments.get("caseScrewKeepawayLength")))
					.movex(measurments.get("topHoleCircleDiameter")/2)
					.movez(mountPlateToHornTopValue)
	def mountScrew = new Cylinder(2.5,10+measurments.get("caseScrewKeepawayLength")).toCSG()
					.movez(mountPlateToHornTopValue)
     def screws=[]
     for(int i=0;i<360;i+=90){
     	screws.add(bodyScrew.rotz(i))
     }
	return CSG.unionAll([part,CSG.unionAll(screws),mountScrew])
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 