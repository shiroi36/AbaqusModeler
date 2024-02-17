
#-------------------------------------------------------------------------------------------
#パートモジュール
mdb.models['Model-1'].ConstrainedSketch(name='__profile__', sheetSize=200.0)
mdb.models['Model-1'].sketches['__profile__'].sketchOptions.setValues(
    gridOrigin=(0.0, 0.0))
mdb.models['Model-1'].sketches['__profile__'].retrieveSketch(sketch=
    mdb.models['Model-1'].sketches['cwr4-1'])
mdb.models['Model-1'].Part(dimensionality=THREE_D, name='cwr', type=
    DEFORMABLE_BODY)
mdb.models['Model-1'].parts['cwr'].BaseSolidExtrude(depth=tcwr, sketch=
    mdb.models['Model-1'].sketches['__profile__'])
del mdb.models['Model-1'].sketches['__profile__']
# Save by 14analysis on 2018_10_12-14.39.03; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].ConstrainedSketch(gridSpacing=9.94, name='__profile__', 
    sheetSize=397.99, transform=
    mdb.models['Model-1'].parts['cwr'].MakeSketchTransform(
    sketchPlane=mdb.models['Model-1'].parts['cwr'].faces[8], 
    sketchPlaneSide=SIDE1, 
    sketchUpEdge=mdb.models['Model-1'].parts['cwr'].edges[10], 
    sketchOrientation=RIGHT, origin=(0.0, 0.0, 20.0)))
mdb.models['Model-1'].parts['cwr'].projectReferencesOntoSketch(filter=
    COPLANAR_EDGES, sketch=mdb.models['Model-1'].sketches['__profile__'])
mdb.models['Model-1'].sketches['__profile__'].retrieveSketch(sketch=
    mdb.models['Model-1'].sketches['cwr4-2'])
mdb.models['Model-1'].parts['cwr'].PartitionFaceBySketch(faces=
    mdb.models['Model-1'].parts['cwr'].faces.getSequenceFromMask(('[#100 ]', ), 
    ), sketch=mdb.models['Model-1'].sketches['__profile__'], sketchUpEdge=
    mdb.models['Model-1'].parts['cwr'].edges[10])
del mdb.models['Model-1'].sketches['__profile__']
# Save by 14analysis on 2018_10_12-14.40.26; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['cwr'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask(('[#1 ]', ), )
    , edges=(mdb.models['Model-1'].parts['cwr'].edges[2], ), line=
    mdb.models['Model-1'].parts['cwr'].edges[13], sense=REVERSE)
mdb.models['Model-1'].parts['cwr'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask(('[#2 ]', ), )
    , edges=(mdb.models['Model-1'].parts['cwr'].edges[9], ), line=
    mdb.models['Model-1'].parts['cwr'].edges[20], sense=REVERSE)
mdb.models['Model-1'].parts['cwr'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask(('[#4 ]', ), )
    , edges=(mdb.models['Model-1'].parts['cwr'].edges[19], ), line=
    mdb.models['Model-1'].parts['cwr'].edges[21], sense=REVERSE)
mdb.models['Model-1'].parts['cwr'].PartitionCellByExtrudeEdge(cells=
    mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask(('[#1 ]', ), )
    , edges=(mdb.models['Model-1'].parts['cwr'].edges[15], ), line=
    mdb.models['Model-1'].parts['cwr'].edges[22], sense=REVERSE)
# Save by 14analysis on 2018_10_12-14.41.41; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['cwr'].Surface(name='b0', side1Faces=
    mdb.models['Model-1'].parts['cwr'].faces.getSequenceFromMask(('[#f0000 ]', 
    ), ))
# Save by 14analysis on 2018_10_12-14.42.22; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['cwr'].SectionAssignment(offset=0.0, offsetField=''
    , offsetType=MIDDLE_SURFACE, region=Region(
    cells=mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask(mask=(
    '[#1f ]', ), )), sectionName=mat, thicknessAssignment=FROM_SECTION)
# Save by 14analysis on 2018_10_12-14.43.05; build 6.12-1 2012_03_13-20.23.18 119612

mdb.models['Model-1'].parts['cwr'].seedEdgeByNumber(constraint=FINER, edges=
    mdb.models['Model-1'].parts['cwr'].edges.getSequenceFromMask((
    '[#c03ff0f ]', ), ), number=8)
mdb.models['Model-1'].parts['cwr'].setMeshControls(allowMapped=False, 
    elemShape=TET, regions=
    mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask(('[#1f ]', ), 
    ), technique=FREE)
mdb.models['Model-1'].parts['cwr'].setElementType(elemTypes=(ElemType(
    elemCode=C3D20R, elemLibrary=STANDARD), ElemType(elemCode=C3D15, 
    elemLibrary=STANDARD), ElemType(elemCode=C3D10, elemLibrary=STANDARD)), 
    regions=(mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask((
    '[#1f ]', ), ), ))
mdb.models['Model-1'].parts['cwr'].seedPart(deviationFactor=0.1, minSizeFactor=
    0.1, size=30.0)
mdb.models['Model-1'].parts['cwr'].setElementType(elemTypes=(ElemType(
    elemCode=UNKNOWN_HEX, elemLibrary=EXPLICIT), ElemType(
    elemCode=UNKNOWN_WEDGE, elemLibrary=EXPLICIT), ElemType(elemCode=C3D10M, 
    elemLibrary=EXPLICIT, secondOrderAccuracy=OFF, distortionControl=DEFAULT)), 
    regions=(mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask((
    '[#1f ]', ), ), ))
mdb.models['Model-1'].parts['cwr'].generateMesh()
mdb.models['Model-1'].parts['cwr'].setElementType(elemTypes=(ElemType(
    elemCode=C3D8R, elemLibrary=EXPLICIT), ElemType(elemCode=C3D6, 
    elemLibrary=EXPLICIT), ElemType(elemCode=C3D4, elemLibrary=EXPLICIT, 
    secondOrderAccuracy=OFF, distortionControl=DEFAULT)), regions=(
    mdb.models['Model-1'].parts['cwr'].cells.getSequenceFromMask(('[#1f ]', ), 
    ), ))
# Save by 14analysis on 2018_10_12-14.45.04; build 6.12-1 2012_03_13-20.23.18 119612







