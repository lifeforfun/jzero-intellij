// Generated from ApiParser.g4 by ANTLR 4.10.1
package io.jzero.antlr4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ApiParser}.
 */
public interface ApiParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ApiParser#api}.
	 * @param ctx the parse tree
	 */
	void enterApi(ApiParser.ApiContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#api}.
	 * @param ctx the parse tree
	 */
	void exitApi(ApiParser.ApiContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#syntaxLit}.
	 * @param ctx the parse tree
	 */
	void enterSyntaxLit(ApiParser.SyntaxLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#syntaxLit}.
	 * @param ctx the parse tree
	 */
	void exitSyntaxLit(ApiParser.SyntaxLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#apiBody}.
	 * @param ctx the parse tree
	 */
	void enterApiBody(ApiParser.ApiBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#apiBody}.
	 * @param ctx the parse tree
	 */
	void exitApiBody(ApiParser.ApiBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void enterImportStatement(ApiParser.ImportStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void exitImportStatement(ApiParser.ImportStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#importSpec}.
	 * @param ctx the parse tree
	 */
	void enterImportSpec(ApiParser.ImportSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#importSpec}.
	 * @param ctx the parse tree
	 */
	void exitImportSpec(ApiParser.ImportSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#importLit}.
	 * @param ctx the parse tree
	 */
	void enterImportLit(ApiParser.ImportLitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#importLit}.
	 * @param ctx the parse tree
	 */
	void exitImportLit(ApiParser.ImportLitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#importGroup}.
	 * @param ctx the parse tree
	 */
	void enterImportGroup(ApiParser.ImportGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#importGroup}.
	 * @param ctx the parse tree
	 */
	void exitImportGroup(ApiParser.ImportGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#infoStatement}.
	 * @param ctx the parse tree
	 */
	void enterInfoStatement(ApiParser.InfoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#infoStatement}.
	 * @param ctx the parse tree
	 */
	void exitInfoStatement(ApiParser.InfoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeStatement}.
	 * @param ctx the parse tree
	 */
	void enterTypeStatement(ApiParser.TypeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeStatement}.
	 * @param ctx the parse tree
	 */
	void exitTypeStatement(ApiParser.TypeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeGroupSpec}.
	 * @param ctx the parse tree
	 */
	void enterTypeGroupSpec(ApiParser.TypeGroupSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeGroupSpec}.
	 * @param ctx the parse tree
	 */
	void exitTypeGroupSpec(ApiParser.TypeGroupSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#groupName}.
	 * @param ctx the parse tree
	 */
	void enterGroupName(ApiParser.GroupNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#groupName}.
	 * @param ctx the parse tree
	 */
	void exitGroupName(ApiParser.GroupNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeGroupBody}.
	 * @param ctx the parse tree
	 */
	void enterTypeGroupBody(ApiParser.TypeGroupBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeGroupBody}.
	 * @param ctx the parse tree
	 */
	void exitTypeGroupBody(ApiParser.TypeGroupBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeSingleSpec}.
	 * @param ctx the parse tree
	 */
	void enterTypeSingleSpec(ApiParser.TypeSingleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeSingleSpec}.
	 * @param ctx the parse tree
	 */
	void exitTypeSingleSpec(ApiParser.TypeSingleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeStruct}.
	 * @param ctx the parse tree
	 */
	void enterTypeStruct(ApiParser.TypeStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeStruct}.
	 * @param ctx the parse tree
	 */
	void exitTypeStruct(ApiParser.TypeStructContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeAlias}.
	 * @param ctx the parse tree
	 */
	void enterTypeAlias(ApiParser.TypeAliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeAlias}.
	 * @param ctx the parse tree
	 */
	void exitTypeAlias(ApiParser.TypeAliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#voSingle}.
	 * @param ctx the parse tree
	 */
	void enterVoSingle(ApiParser.VoSingleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#voSingle}.
	 * @param ctx the parse tree
	 */
	void exitVoSingle(ApiParser.VoSingleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#dtoSingle}.
	 * @param ctx the parse tree
	 */
	void enterDtoSingle(ApiParser.DtoSingleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#dtoSingle}.
	 * @param ctx the parse tree
	 */
	void exitDtoSingle(ApiParser.DtoSingleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeGroupAlias}.
	 * @param ctx the parse tree
	 */
	void enterTypeGroupAlias(ApiParser.TypeGroupAliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeGroupAlias}.
	 * @param ctx the parse tree
	 */
	void exitTypeGroupAlias(ApiParser.TypeGroupAliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#typeFiled}.
	 * @param ctx the parse tree
	 */
	void enterTypeFiled(ApiParser.TypeFiledContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#typeFiled}.
	 * @param ctx the parse tree
	 */
	void exitTypeFiled(ApiParser.TypeFiledContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#normalField}.
	 * @param ctx the parse tree
	 */
	void enterNormalField(ApiParser.NormalFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#normalField}.
	 * @param ctx the parse tree
	 */
	void exitNormalField(ApiParser.NormalFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#fieldType}.
	 * @param ctx the parse tree
	 */
	void enterFieldType(ApiParser.FieldTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#fieldType}.
	 * @param ctx the parse tree
	 */
	void exitFieldType(ApiParser.FieldTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#anonymousField}.
	 * @param ctx the parse tree
	 */
	void enterAnonymousField(ApiParser.AnonymousFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#anonymousField}.
	 * @param ctx the parse tree
	 */
	void exitAnonymousField(ApiParser.AnonymousFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#normalFieldType}.
	 * @param ctx the parse tree
	 */
	void enterNormalFieldType(ApiParser.NormalFieldTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#normalFieldType}.
	 * @param ctx the parse tree
	 */
	void exitNormalFieldType(ApiParser.NormalFieldTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#starFieldType}.
	 * @param ctx the parse tree
	 */
	void enterStarFieldType(ApiParser.StarFieldTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#starFieldType}.
	 * @param ctx the parse tree
	 */
	void exitStarFieldType(ApiParser.StarFieldTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#mapFieldType}.
	 * @param ctx the parse tree
	 */
	void enterMapFieldType(ApiParser.MapFieldTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#mapFieldType}.
	 * @param ctx the parse tree
	 */
	void exitMapFieldType(ApiParser.MapFieldTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#arrayOrSliceType}.
	 * @param ctx the parse tree
	 */
	void enterArrayOrSliceType(ApiParser.ArrayOrSliceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#arrayOrSliceType}.
	 * @param ctx the parse tree
	 */
	void exitArrayOrSliceType(ApiParser.ArrayOrSliceTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#structType}.
	 * @param ctx the parse tree
	 */
	void enterStructType(ApiParser.StructTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#structType}.
	 * @param ctx the parse tree
	 */
	void exitStructType(ApiParser.StructTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#structNameId}.
	 * @param ctx the parse tree
	 */
	void enterStructNameId(ApiParser.StructNameIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#structNameId}.
	 * @param ctx the parse tree
	 */
	void exitStructNameId(ApiParser.StructNameIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void enterFieldName(ApiParser.FieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void exitFieldName(ApiParser.FieldNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#referenceId}.
	 * @param ctx the parse tree
	 */
	void enterReferenceId(ApiParser.ReferenceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#referenceId}.
	 * @param ctx the parse tree
	 */
	void exitReferenceId(ApiParser.ReferenceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#pkg}.
	 * @param ctx the parse tree
	 */
	void enterPkg(ApiParser.PkgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#pkg}.
	 * @param ctx the parse tree
	 */
	void exitPkg(ApiParser.PkgContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(ApiParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(ApiParser.TagContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ApiParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ApiParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceStatement}.
	 * @param ctx the parse tree
	 */
	void enterServiceStatement(ApiParser.ServiceStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceStatement}.
	 * @param ctx the parse tree
	 */
	void exitServiceStatement(ApiParser.ServiceStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceServerSpec}.
	 * @param ctx the parse tree
	 */
	void enterServiceServerSpec(ApiParser.ServiceServerSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceServerSpec}.
	 * @param ctx the parse tree
	 */
	void exitServiceServerSpec(ApiParser.ServiceServerSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceSpec}.
	 * @param ctx the parse tree
	 */
	void enterServiceSpec(ApiParser.ServiceSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceSpec}.
	 * @param ctx the parse tree
	 */
	void exitServiceSpec(ApiParser.ServiceSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceName}.
	 * @param ctx the parse tree
	 */
	void enterServiceName(ApiParser.ServiceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceName}.
	 * @param ctx the parse tree
	 */
	void exitServiceName(ApiParser.ServiceNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceBody}.
	 * @param ctx the parse tree
	 */
	void enterServiceBody(ApiParser.ServiceBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceBody}.
	 * @param ctx the parse tree
	 */
	void exitServiceBody(ApiParser.ServiceBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceDoc}.
	 * @param ctx the parse tree
	 */
	void enterServiceDoc(ApiParser.ServiceDocContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceDoc}.
	 * @param ctx the parse tree
	 */
	void exitServiceDoc(ApiParser.ServiceDocContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceDocNew}.
	 * @param ctx the parse tree
	 */
	void enterServiceDocNew(ApiParser.ServiceDocNewContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceDocNew}.
	 * @param ctx the parse tree
	 */
	void exitServiceDocNew(ApiParser.ServiceDocNewContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceHandler}.
	 * @param ctx the parse tree
	 */
	void enterServiceHandler(ApiParser.ServiceHandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceHandler}.
	 * @param ctx the parse tree
	 */
	void exitServiceHandler(ApiParser.ServiceHandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceHandlerNew}.
	 * @param ctx the parse tree
	 */
	void enterServiceHandlerNew(ApiParser.ServiceHandlerNewContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceHandlerNew}.
	 * @param ctx the parse tree
	 */
	void exitServiceHandlerNew(ApiParser.ServiceHandlerNewContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#serviceRoute}.
	 * @param ctx the parse tree
	 */
	void enterServiceRoute(ApiParser.ServiceRouteContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#serviceRoute}.
	 * @param ctx the parse tree
	 */
	void exitServiceRoute(ApiParser.ServiceRouteContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#httpRoute}.
	 * @param ctx the parse tree
	 */
	void enterHttpRoute(ApiParser.HttpRouteContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#httpRoute}.
	 * @param ctx the parse tree
	 */
	void exitHttpRoute(ApiParser.HttpRouteContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#identPair}.
	 * @param ctx the parse tree
	 */
	void enterIdentPair(ApiParser.IdentPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#identPair}.
	 * @param ctx the parse tree
	 */
	void exitIdentPair(ApiParser.IdentPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#handlerPair}.
	 * @param ctx the parse tree
	 */
	void enterHandlerPair(ApiParser.HandlerPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#handlerPair}.
	 * @param ctx the parse tree
	 */
	void exitHandlerPair(ApiParser.HandlerPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#identValue}.
	 * @param ctx the parse tree
	 */
	void enterIdentValue(ApiParser.IdentValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#identValue}.
	 * @param ctx the parse tree
	 */
	void exitIdentValue(ApiParser.IdentValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#handlerValue}.
	 * @param ctx the parse tree
	 */
	void enterHandlerValue(ApiParser.HandlerValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#handlerValue}.
	 * @param ctx the parse tree
	 */
	void exitHandlerValue(ApiParser.HandlerValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#importValue}.
	 * @param ctx the parse tree
	 */
	void enterImportValue(ApiParser.ImportValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#importValue}.
	 * @param ctx the parse tree
	 */
	void exitImportValue(ApiParser.ImportValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#docValue}.
	 * @param ctx the parse tree
	 */
	void enterDocValue(ApiParser.DocValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#docValue}.
	 * @param ctx the parse tree
	 */
	void exitDocValue(ApiParser.DocValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(ApiParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(ApiParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link ApiParser#key}.
	 * @param ctx the parse tree
	 */
	void enterKey(ApiParser.KeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ApiParser#key}.
	 * @param ctx the parse tree
	 */
	void exitKey(ApiParser.KeyContext ctx);
}