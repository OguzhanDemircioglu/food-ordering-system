
# Latest version
mvn com.github.ferstl:depgraph-maven-plugin:graph

# Specific version
mvn com.github.ferstl:depgraph-maven-plugin:4.0.3:graph

mvn com.github.ferstl:depgraph-maven-pl
ugin:aggregate -DcreateImage=true -DreduceEdg
es=false -Dscope=compile "-Dincludes=com.food.ordering.system*:*"
