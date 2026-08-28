// Generated from ApiParser.g4 by ANTLR 4.10.1
package io.jzero.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ApiParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SYNTAX=1, INFO=2, MAP=3, STRUCT=4, ANY=5, INTERFACE=6, TYPE=7, VO=8, DTO=9, 
		ATSERVER=10, ATDOC=11, ATHANDLER=12, SERVICE=13, RETURNS=14, IMPORT=15, 
		AS=16, HTTPMETHOD=17, GET=18, HEAD=19, POST=20, PUT=21, PATCH=22, DELETE=23, 
		CONNECT=24, OPTIONS=25, TRACE=26, GOTYPE=27, BOOL=28, UINT8=29, UINT16=30, 
		UINT32=31, UINT64=32, INT8=33, INT16=34, INT32=35, INT64=36, FLOAT32=37, 
		FLOAT64=38, COMPLEX64=39, COMPLEX128=40, STRING=41, INT=42, UINT=43, UINTPTR=44, 
		BYTE=45, RUNE=46, TIME=47, PATH=48, DURATION=49, LPAREN=50, RPAREN=51, 
		LBRACE=52, RBRACE=53, LBRACK=54, RBRACK=55, DOT=56, SMICOLON=57, COMMA=58, 
		STAR=59, BAR=60, ASSIGN=61, COLON=62, SLASH=63, NUMBER=64, HOSTVALUE=65, 
		IDENT=66, WS=67, VALUE=68, RAW_STRING=69, COMMENT=70, DOC_COMMENT=71, 
		ERRCHAR=72;
	public static final int
		RULE_api = 0, RULE_syntaxLit = 1, RULE_apiBody = 2, RULE_importStatement = 3, 
		RULE_importSpec = 4, RULE_importLit = 5, RULE_importGroup = 6, RULE_infoStatement = 7, 
		RULE_typeStatement = 8, RULE_typeGroupSpec = 9, RULE_groupName = 10, RULE_typeGroupBody = 11, 
		RULE_typeSingleSpec = 12, RULE_typeStruct = 13, RULE_typeAlias = 14, RULE_voSingle = 15, 
		RULE_dtoSingle = 16, RULE_typeGroupAlias = 17, RULE_typeFiled = 18, RULE_normalField = 19, 
		RULE_fieldType = 20, RULE_anonymousField = 21, RULE_normalFieldType = 22, 
		RULE_starFieldType = 23, RULE_mapFieldType = 24, RULE_arrayOrSliceType = 25, 
		RULE_structType = 26, RULE_structNameId = 27, RULE_fieldName = 28, RULE_referenceId = 29, 
		RULE_pkg = 30, RULE_tag = 31, RULE_body = 32, RULE_serviceStatement = 33, 
		RULE_serviceServerSpec = 34, RULE_serviceSpec = 35, RULE_serviceName = 36, 
		RULE_serviceBody = 37, RULE_serviceDoc = 38, RULE_serviceDocNew = 39, 
		RULE_serviceHandler = 40, RULE_serviceHandlerNew = 41, RULE_serviceRoute = 42, 
		RULE_httpRoute = 43, RULE_identPair = 44, RULE_handlerPair = 45, RULE_identValue = 46, 
		RULE_handlerValue = 47, RULE_importValue = 48, RULE_docValue = 49, RULE_pair = 50, 
		RULE_key = 51;
	private static String[] makeRuleNames() {
		return new String[] {
			"api", "syntaxLit", "apiBody", "importStatement", "importSpec", "importLit", 
			"importGroup", "infoStatement", "typeStatement", "typeGroupSpec", "groupName", 
			"typeGroupBody", "typeSingleSpec", "typeStruct", "typeAlias", "voSingle", 
			"dtoSingle", "typeGroupAlias", "typeFiled", "normalField", "fieldType", 
			"anonymousField", "normalFieldType", "starFieldType", "mapFieldType", 
			"arrayOrSliceType", "structType", "structNameId", "fieldName", "referenceId", 
			"pkg", "tag", "body", "serviceStatement", "serviceServerSpec", "serviceSpec", 
			"serviceName", "serviceBody", "serviceDoc", "serviceDocNew", "serviceHandler", 
			"serviceHandlerNew", "serviceRoute", "httpRoute", "identPair", "handlerPair", 
			"identValue", "handlerValue", "importValue", "docValue", "pair", "key"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'syntax'", "'info'", "'map'", "'struct'", "'interface{}'", "'interface'", 
			"'type'", "'vo'", "'dto'", "'@server'", "'@doc'", "'@handler'", "'service'", 
			"'returns'", "'import'", "'as'", null, "'get'", "'head'", "'post'", "'put'", 
			"'patch'", "'delete'", "'connect'", "'options'", "'trace'", null, "'bool'", 
			"'uint8'", "'uint16'", "'uint32'", "'uint64'", "'int8'", "'int16'", "'int32'", 
			"'int64'", "'float32'", "'float64'", "'complex64'", "'complex128'", "'string'", 
			"'int'", "'uint'", "'uintptr'", "'byte'", "'rune'", "'time.Time'", null, 
			null, "'('", "')'", "'{'", "'}'", "'['", "']'", "'.'", "';'", "','", 
			"'*'", "'-'", "'='", "':'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SYNTAX", "INFO", "MAP", "STRUCT", "ANY", "INTERFACE", "TYPE", 
			"VO", "DTO", "ATSERVER", "ATDOC", "ATHANDLER", "SERVICE", "RETURNS", 
			"IMPORT", "AS", "HTTPMETHOD", "GET", "HEAD", "POST", "PUT", "PATCH", 
			"DELETE", "CONNECT", "OPTIONS", "TRACE", "GOTYPE", "BOOL", "UINT8", "UINT16", 
			"UINT32", "UINT64", "INT8", "INT16", "INT32", "INT64", "FLOAT32", "FLOAT64", 
			"COMPLEX64", "COMPLEX128", "STRING", "INT", "UINT", "UINTPTR", "BYTE", 
			"RUNE", "TIME", "PATH", "DURATION", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"LBRACK", "RBRACK", "DOT", "SMICOLON", "COMMA", "STAR", "BAR", "ASSIGN", 
			"COLON", "SLASH", "NUMBER", "HOSTVALUE", "IDENT", "WS", "VALUE", "RAW_STRING", 
			"COMMENT", "DOC_COMMENT", "ERRCHAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ApiParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	boolean isNormalField() {
	    int line = _input.LT(1).getLine();
	    java.util.List<Integer> types = new java.util.ArrayList<>();
	    int idx = 1;
	    while (true) {
	        org.antlr.v4.runtime.Token t = _input.LT(idx);
	        if (t.getType() == org.antlr.v4.runtime.Token.EOF) {
	            break;
	        }
	        if (t.getLine() != line) {
	            break;
	        }
	        types.add(t.getType());
	        idx++;
	    }
	    if (types.isEmpty()) {
	        return false;
	    }
	    if (types.size() == 1) {
	        return false;
	    }
	    if (types.get(0) == ApiLexer.STAR) {
	        return false;
	    }
	    return true;
	}

	public ApiParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ApiContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ApiParser.EOF, 0); }
		public SyntaxLitContext syntaxLit() {
			return getRuleContext(SyntaxLitContext.class,0);
		}
		public List<ImportStatementContext> importStatement() {
			return getRuleContexts(ImportStatementContext.class);
		}
		public ImportStatementContext importStatement(int i) {
			return getRuleContext(ImportStatementContext.class,i);
		}
		public List<InfoStatementContext> infoStatement() {
			return getRuleContexts(InfoStatementContext.class);
		}
		public InfoStatementContext infoStatement(int i) {
			return getRuleContext(InfoStatementContext.class,i);
		}
		public List<ApiBodyContext> apiBody() {
			return getRuleContexts(ApiBodyContext.class);
		}
		public ApiBodyContext apiBody(int i) {
			return getRuleContext(ApiBodyContext.class,i);
		}
		public ApiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_api; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterApi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitApi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitApi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApiContext api() throws RecognitionException {
		ApiContext _localctx = new ApiContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_api);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SYNTAX) {
				{
				setState(104);
				syntaxLit();
				}
			}

			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INFO) | (1L << TYPE) | (1L << VO) | (1L << DTO) | (1L << ATSERVER) | (1L << SERVICE) | (1L << IMPORT))) != 0)) {
				{
				setState(110);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IMPORT:
					{
					setState(107);
					importStatement();
					}
					break;
				case INFO:
					{
					setState(108);
					infoStatement();
					}
					break;
				case TYPE:
				case VO:
				case DTO:
				case ATSERVER:
				case SERVICE:
					{
					setState(109);
					apiBody();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SyntaxLitContext extends ParserRuleContext {
		public TerminalNode SYNTAX() { return getToken(ApiParser.SYNTAX, 0); }
		public TerminalNode ASSIGN() { return getToken(ApiParser.ASSIGN, 0); }
		public TerminalNode VALUE() { return getToken(ApiParser.VALUE, 0); }
		public SyntaxLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_syntaxLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterSyntaxLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitSyntaxLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitSyntaxLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SyntaxLitContext syntaxLit() throws RecognitionException {
		SyntaxLitContext _localctx = new SyntaxLitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_syntaxLit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(SYNTAX);
			setState(118);
			match(ASSIGN);
			setState(119);
			match(VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApiBodyContext extends ParserRuleContext {
		public TypeStatementContext typeStatement() {
			return getRuleContext(TypeStatementContext.class,0);
		}
		public ServiceStatementContext serviceStatement() {
			return getRuleContext(ServiceStatementContext.class,0);
		}
		public ApiBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_apiBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterApiBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitApiBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitApiBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApiBodyContext apiBody() throws RecognitionException {
		ApiBodyContext _localctx = new ApiBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_apiBody);
		try {
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
			case VO:
			case DTO:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				typeStatement();
				}
				break;
			case ATSERVER:
			case SERVICE:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				serviceStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportStatementContext extends ParserRuleContext {
		public List<ImportSpecContext> importSpec() {
			return getRuleContexts(ImportSpecContext.class);
		}
		public ImportSpecContext importSpec(int i) {
			return getRuleContext(ImportSpecContext.class,i);
		}
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterImportStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitImportStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitImportStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_importStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(125);
					importSpec();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(128); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportSpecContext extends ParserRuleContext {
		public ImportLitContext importLit() {
			return getRuleContext(ImportLitContext.class,0);
		}
		public ImportGroupContext importGroup() {
			return getRuleContext(ImportGroupContext.class,0);
		}
		public ImportSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterImportSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitImportSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitImportSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportSpecContext importSpec() throws RecognitionException {
		ImportSpecContext _localctx = new ImportSpecContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_importSpec);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(130);
				importLit();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(131);
				importGroup();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportLitContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(ApiParser.IMPORT, 0); }
		public ImportValueContext importValue() {
			return getRuleContext(ImportValueContext.class,0);
		}
		public ImportLitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importLit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterImportLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitImportLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitImportLit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportLitContext importLit() throws RecognitionException {
		ImportLitContext _localctx = new ImportLitContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_importLit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(IMPORT);
			setState(135);
			importValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportGroupContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(ApiParser.IMPORT, 0); }
		public TerminalNode LPAREN() { return getToken(ApiParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ApiParser.RPAREN, 0); }
		public List<ImportValueContext> importValue() {
			return getRuleContexts(ImportValueContext.class);
		}
		public ImportValueContext importValue(int i) {
			return getRuleContext(ImportValueContext.class,i);
		}
		public ImportGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterImportGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitImportGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitImportGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportGroupContext importGroup() throws RecognitionException {
		ImportGroupContext _localctx = new ImportGroupContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_importGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(IMPORT);
			setState(138);
			match(LPAREN);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VALUE) {
				{
				{
				setState(139);
				importValue();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(145);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InfoStatementContext extends ParserRuleContext {
		public TerminalNode INFO() { return getToken(ApiParser.INFO, 0); }
		public TerminalNode LPAREN() { return getToken(ApiParser.LPAREN, 0); }
		public PairContext pair() {
			return getRuleContext(PairContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ApiParser.RPAREN, 0); }
		public InfoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_infoStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterInfoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitInfoStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitInfoStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InfoStatementContext infoStatement() throws RecognitionException {
		InfoStatementContext _localctx = new InfoStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_infoStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(INFO);
			setState(148);
			match(LPAREN);
			setState(149);
			pair();
			setState(150);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeStatementContext extends ParserRuleContext {
		public TypeSingleSpecContext typeSingleSpec() {
			return getRuleContext(TypeSingleSpecContext.class,0);
		}
		public TypeGroupSpecContext typeGroupSpec() {
			return getRuleContext(TypeGroupSpecContext.class,0);
		}
		public TypeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeStatementContext typeStatement() throws RecognitionException {
		TypeStatementContext _localctx = new TypeStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeStatement);
		try {
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				typeSingleSpec();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				typeGroupSpec();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeGroupSpecContext extends ParserRuleContext {
		public GroupNameContext groupName() {
			return getRuleContext(GroupNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ApiParser.LPAREN, 0); }
		public TypeGroupBodyContext typeGroupBody() {
			return getRuleContext(TypeGroupBodyContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ApiParser.RPAREN, 0); }
		public TypeGroupSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeGroupSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeGroupSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeGroupSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeGroupSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeGroupSpecContext typeGroupSpec() throws RecognitionException {
		TypeGroupSpecContext _localctx = new TypeGroupSpecContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeGroupSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			groupName();
			setState(157);
			match(LPAREN);
			setState(158);
			typeGroupBody();
			setState(159);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GroupNameContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(ApiParser.TYPE, 0); }
		public TerminalNode VO() { return getToken(ApiParser.VO, 0); }
		public TerminalNode DTO() { return getToken(ApiParser.DTO, 0); }
		public GroupNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterGroupName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitGroupName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitGroupName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupNameContext groupName() throws RecognitionException {
		GroupNameContext _localctx = new GroupNameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_groupName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE) | (1L << VO) | (1L << DTO))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeGroupBodyContext extends ParserRuleContext {
		public List<TypeGroupAliasContext> typeGroupAlias() {
			return getRuleContexts(TypeGroupAliasContext.class);
		}
		public TypeGroupAliasContext typeGroupAlias(int i) {
			return getRuleContext(TypeGroupAliasContext.class,i);
		}
		public List<StructTypeContext> structType() {
			return getRuleContexts(StructTypeContext.class);
		}
		public StructTypeContext structType(int i) {
			return getRuleContext(StructTypeContext.class,i);
		}
		public TypeGroupBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeGroupBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeGroupBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeGroupBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeGroupBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeGroupBodyContext typeGroupBody() throws RecognitionException {
		TypeGroupBodyContext _localctx = new TypeGroupBodyContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeGroupBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				setState(165);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(163);
					typeGroupAlias();
					}
					break;
				case 2:
					{
					setState(164);
					structType();
					}
					break;
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeSingleSpecContext extends ParserRuleContext {
		public TypeAliasContext typeAlias() {
			return getRuleContext(TypeAliasContext.class,0);
		}
		public TypeStructContext typeStruct() {
			return getRuleContext(TypeStructContext.class,0);
		}
		public VoSingleContext voSingle() {
			return getRuleContext(VoSingleContext.class,0);
		}
		public DtoSingleContext dtoSingle() {
			return getRuleContext(DtoSingleContext.class,0);
		}
		public TypeSingleSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSingleSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeSingleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeSingleSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeSingleSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeSingleSpecContext typeSingleSpec() throws RecognitionException {
		TypeSingleSpecContext _localctx = new TypeSingleSpecContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_typeSingleSpec);
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				typeAlias();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				typeStruct();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(172);
				voSingle();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(173);
				dtoSingle();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeStructContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(ApiParser.TYPE, 0); }
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public TypeStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeStructContext typeStruct() throws RecognitionException {
		TypeStructContext _localctx = new TypeStructContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_typeStruct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(TYPE);
			setState(177);
			structType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeAliasContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(ApiParser.TYPE, 0); }
		public StructNameIdContext structNameId() {
			return getRuleContext(StructNameIdContext.class,0);
		}
		public NormalFieldTypeContext normalFieldType() {
			return getRuleContext(NormalFieldTypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ApiParser.ASSIGN, 0); }
		public TypeAliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAlias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeAlias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeAlias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeAliasContext typeAlias() throws RecognitionException {
		TypeAliasContext _localctx = new TypeAliasContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_typeAlias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			match(TYPE);
			setState(180);
			structNameId();
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(181);
				match(ASSIGN);
				}
			}

			setState(184);
			normalFieldType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VoSingleContext extends ParserRuleContext {
		public TerminalNode VO() { return getToken(ApiParser.VO, 0); }
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public VoSingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_voSingle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterVoSingle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitVoSingle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitVoSingle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VoSingleContext voSingle() throws RecognitionException {
		VoSingleContext _localctx = new VoSingleContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_voSingle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(VO);
			setState(187);
			structType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DtoSingleContext extends ParserRuleContext {
		public TerminalNode DTO() { return getToken(ApiParser.DTO, 0); }
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public StructNameIdContext structNameId() {
			return getRuleContext(StructNameIdContext.class,0);
		}
		public NormalFieldTypeContext normalFieldType() {
			return getRuleContext(NormalFieldTypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ApiParser.ASSIGN, 0); }
		public DtoSingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dtoSingle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterDtoSingle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitDtoSingle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitDtoSingle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DtoSingleContext dtoSingle() throws RecognitionException {
		DtoSingleContext _localctx = new DtoSingleContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_dtoSingle);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(DTO);
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(190);
				structType();
				}
				break;
			case 2:
				{
				setState(191);
				structNameId();
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(192);
					match(ASSIGN);
					}
				}

				setState(195);
				normalFieldType();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeGroupAliasContext extends ParserRuleContext {
		public StructNameIdContext structNameId() {
			return getRuleContext(StructNameIdContext.class,0);
		}
		public NormalFieldTypeContext normalFieldType() {
			return getRuleContext(NormalFieldTypeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ApiParser.ASSIGN, 0); }
		public TypeGroupAliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeGroupAlias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeGroupAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeGroupAlias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeGroupAlias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeGroupAliasContext typeGroupAlias() throws RecognitionException {
		TypeGroupAliasContext _localctx = new TypeGroupAliasContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeGroupAlias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			structNameId();
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(200);
				match(ASSIGN);
				}
			}

			setState(203);
			normalFieldType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeFiledContext extends ParserRuleContext {
		public NormalFieldContext normalField() {
			return getRuleContext(NormalFieldContext.class,0);
		}
		public AnonymousFieldContext anonymousField() {
			return getRuleContext(AnonymousFieldContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public TypeFiledContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeFiled; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTypeFiled(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTypeFiled(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTypeFiled(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeFiledContext typeFiled() throws RecognitionException {
		TypeFiledContext _localctx = new TypeFiledContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeFiled);
		try {
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(205);
				if (!(isNormalField())) throw new FailedPredicateException(this, "isNormalField()");
				setState(206);
				normalField();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(207);
				anonymousField();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(208);
				structType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NormalFieldContext extends ParserRuleContext {
		public List<FieldNameContext> fieldName() {
			return getRuleContexts(FieldNameContext.class);
		}
		public FieldNameContext fieldName(int i) {
			return getRuleContext(FieldNameContext.class,i);
		}
		public FieldTypeContext fieldType() {
			return getRuleContext(FieldTypeContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(ApiParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ApiParser.COMMA, i);
		}
		public TagContext tag() {
			return getRuleContext(TagContext.class,0);
		}
		public NormalFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normalField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterNormalField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitNormalField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitNormalField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NormalFieldContext normalField() throws RecognitionException {
		NormalFieldContext _localctx = new NormalFieldContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_normalField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			fieldName();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(212);
				match(COMMA);
				setState(213);
				fieldName();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(219);
			fieldType();
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(220);
				tag();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldTypeContext extends ParserRuleContext {
		public NormalFieldTypeContext normalFieldType() {
			return getRuleContext(NormalFieldTypeContext.class,0);
		}
		public StarFieldTypeContext starFieldType() {
			return getRuleContext(StarFieldTypeContext.class,0);
		}
		public MapFieldTypeContext mapFieldType() {
			return getRuleContext(MapFieldTypeContext.class,0);
		}
		public ArrayOrSliceTypeContext arrayOrSliceType() {
			return getRuleContext(ArrayOrSliceTypeContext.class,0);
		}
		public FieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterFieldType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitFieldType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitFieldType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldTypeContext fieldType() throws RecognitionException {
		FieldTypeContext _localctx = new FieldTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fieldType);
		try {
			setState(227);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ANY:
			case INTERFACE:
			case GOTYPE:
			case IDENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(223);
				normalFieldType();
				}
				break;
			case STAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(224);
				starFieldType();
				}
				break;
			case MAP:
				enterOuterAlt(_localctx, 3);
				{
				setState(225);
				mapFieldType();
				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 4);
				{
				setState(226);
				arrayOrSliceType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnonymousFieldContext extends ParserRuleContext {
		public ReferenceIdContext referenceId() {
			return getRuleContext(ReferenceIdContext.class,0);
		}
		public TerminalNode STAR() { return getToken(ApiParser.STAR, 0); }
		public TagContext tag() {
			return getRuleContext(TagContext.class,0);
		}
		public AnonymousFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterAnonymousField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitAnonymousField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitAnonymousField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnonymousFieldContext anonymousField() throws RecognitionException {
		AnonymousFieldContext _localctx = new AnonymousFieldContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_anonymousField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(229);
				match(STAR);
				}
			}

			setState(232);
			referenceId();
			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(233);
				tag();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NormalFieldTypeContext extends ParserRuleContext {
		public TerminalNode GOTYPE() { return getToken(ApiParser.GOTYPE, 0); }
		public ReferenceIdContext referenceId() {
			return getRuleContext(ReferenceIdContext.class,0);
		}
		public TerminalNode ANY() { return getToken(ApiParser.ANY, 0); }
		public TerminalNode INTERFACE() { return getToken(ApiParser.INTERFACE, 0); }
		public TerminalNode LBRACE() { return getToken(ApiParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ApiParser.RBRACE, 0); }
		public NormalFieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_normalFieldType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterNormalFieldType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitNormalFieldType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitNormalFieldType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NormalFieldTypeContext normalFieldType() throws RecognitionException {
		NormalFieldTypeContext _localctx = new NormalFieldTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_normalFieldType);
		try {
			setState(242);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GOTYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(GOTYPE);
				}
				break;
			case IDENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				referenceId();
				}
				break;
			case ANY:
				enterOuterAlt(_localctx, 3);
				{
				setState(238);
				match(ANY);
				}
				break;
			case INTERFACE:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(239);
				match(INTERFACE);
				setState(240);
				match(LBRACE);
				setState(241);
				match(RBRACE);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StarFieldTypeContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(ApiParser.STAR, 0); }
		public NormalFieldTypeContext normalFieldType() {
			return getRuleContext(NormalFieldTypeContext.class,0);
		}
		public StarFieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_starFieldType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterStarFieldType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitStarFieldType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitStarFieldType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StarFieldTypeContext starFieldType() throws RecognitionException {
		StarFieldTypeContext _localctx = new StarFieldTypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_starFieldType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(STAR);
			setState(245);
			normalFieldType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapFieldTypeContext extends ParserRuleContext {
		public TerminalNode MAP() { return getToken(ApiParser.MAP, 0); }
		public TerminalNode LBRACK() { return getToken(ApiParser.LBRACK, 0); }
		public List<FieldTypeContext> fieldType() {
			return getRuleContexts(FieldTypeContext.class);
		}
		public FieldTypeContext fieldType(int i) {
			return getRuleContext(FieldTypeContext.class,i);
		}
		public TerminalNode RBRACK() { return getToken(ApiParser.RBRACK, 0); }
		public MapFieldTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapFieldType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterMapFieldType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitMapFieldType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitMapFieldType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapFieldTypeContext mapFieldType() throws RecognitionException {
		MapFieldTypeContext _localctx = new MapFieldTypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_mapFieldType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(MAP);
			setState(248);
			match(LBRACK);
			setState(249);
			fieldType();
			setState(250);
			match(RBRACK);
			setState(251);
			fieldType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayOrSliceTypeContext extends ParserRuleContext {
		public FieldTypeContext fieldType() {
			return getRuleContext(FieldTypeContext.class,0);
		}
		public List<TerminalNode> LBRACK() { return getTokens(ApiParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(ApiParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(ApiParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(ApiParser.RBRACK, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(ApiParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(ApiParser.NUMBER, i);
		}
		public List<TerminalNode> IDENT() { return getTokens(ApiParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ApiParser.IDENT, i);
		}
		public ArrayOrSliceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayOrSliceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterArrayOrSliceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitArrayOrSliceType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitArrayOrSliceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayOrSliceTypeContext arrayOrSliceType() throws RecognitionException {
		ArrayOrSliceTypeContext _localctx = new ArrayOrSliceTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_arrayOrSliceType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(253);
					match(LBRACK);
					setState(255);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NUMBER || _la==IDENT) {
						{
						setState(254);
						_la = _input.LA(1);
						if ( !(_la==NUMBER || _la==IDENT) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
					}

					setState(257);
					match(RBRACK);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(260); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(262);
			fieldType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructTypeContext extends ParserRuleContext {
		public StructNameIdContext structNameId() {
			return getRuleContext(StructNameIdContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(ApiParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ApiParser.RBRACE, 0); }
		public TerminalNode STRUCT() { return getToken(ApiParser.STRUCT, 0); }
		public List<TypeFiledContext> typeFiled() {
			return getRuleContexts(TypeFiledContext.class);
		}
		public TypeFiledContext typeFiled(int i) {
			return getRuleContext(TypeFiledContext.class,i);
		}
		public StructTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterStructType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitStructType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitStructType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_structType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			structNameId();
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRUCT) {
				{
				setState(265);
				match(STRUCT);
				}
			}

			setState(268);
			match(LBRACE);
			setState(272);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(269);
					typeFiled();
					}
					} 
				}
				setState(274);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(275);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructNameIdContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public StructNameIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structNameId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterStructNameId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitStructNameId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitStructNameId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructNameIdContext structNameId() throws RecognitionException {
		StructNameIdContext _localctx = new StructNameIdContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_structNameId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldNameContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public FieldNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterFieldName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitFieldName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitFieldName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldNameContext fieldName() throws RecognitionException {
		FieldNameContext _localctx = new FieldNameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_fieldName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReferenceIdContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public PkgContext pkg() {
			return getRuleContext(PkgContext.class,0);
		}
		public ReferenceIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_referenceId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterReferenceId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitReferenceId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitReferenceId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReferenceIdContext referenceId() throws RecognitionException {
		ReferenceIdContext _localctx = new ReferenceIdContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_referenceId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(281);
				pkg();
				}
				break;
			}
			setState(284);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PkgContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public TerminalNode DOT() { return getToken(ApiParser.DOT, 0); }
		public PkgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pkg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterPkg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitPkg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitPkg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PkgContext pkg() throws RecognitionException {
		PkgContext _localctx = new PkgContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_pkg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(IDENT);
			setState(287);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagContext extends ParserRuleContext {
		public TerminalNode RAW_STRING() { return getToken(ApiParser.RAW_STRING, 0); }
		public TagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			match(RAW_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public TerminalNode GOTYPE() { return getToken(ApiParser.GOTYPE, 0); }
		public TerminalNode ANY() { return getToken(ApiParser.ANY, 0); }
		public TerminalNode LBRACK() { return getToken(ApiParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(ApiParser.RBRACK, 0); }
		public TerminalNode STAR() { return getToken(ApiParser.STAR, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LBRACK) {
				{
				setState(291);
				match(LBRACK);
				setState(292);
				match(RBRACK);
				}
			}

			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(295);
				match(STAR);
				}
			}

			setState(298);
			_la = _input.LA(1);
			if ( !(((((_la - 5)) & ~0x3f) == 0 && ((1L << (_la - 5)) & ((1L << (ANY - 5)) | (1L << (GOTYPE - 5)) | (1L << (IDENT - 5)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceStatementContext extends ParserRuleContext {
		public ServiceSpecContext serviceSpec() {
			return getRuleContext(ServiceSpecContext.class,0);
		}
		public ServiceServerSpecContext serviceServerSpec() {
			return getRuleContext(ServiceServerSpecContext.class,0);
		}
		public ServiceStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceStatementContext serviceStatement() throws RecognitionException {
		ServiceStatementContext _localctx = new ServiceStatementContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_serviceStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ATSERVER) {
				{
				setState(300);
				serviceServerSpec();
				}
			}

			setState(303);
			serviceSpec();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceServerSpecContext extends ParserRuleContext {
		public TerminalNode ATSERVER() { return getToken(ApiParser.ATSERVER, 0); }
		public TerminalNode LPAREN() { return getToken(ApiParser.LPAREN, 0); }
		public IdentPairContext identPair() {
			return getRuleContext(IdentPairContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ApiParser.RPAREN, 0); }
		public ServiceServerSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceServerSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceServerSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceServerSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceServerSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceServerSpecContext serviceServerSpec() throws RecognitionException {
		ServiceServerSpecContext _localctx = new ServiceServerSpecContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_serviceServerSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(ATSERVER);
			setState(306);
			match(LPAREN);
			setState(307);
			identPair();
			setState(308);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceSpecContext extends ParserRuleContext {
		public TerminalNode SERVICE() { return getToken(ApiParser.SERVICE, 0); }
		public ServiceNameContext serviceName() {
			return getRuleContext(ServiceNameContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(ApiParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(ApiParser.RBRACE, 0); }
		public List<ServiceBodyContext> serviceBody() {
			return getRuleContexts(ServiceBodyContext.class);
		}
		public ServiceBodyContext serviceBody(int i) {
			return getRuleContext(ServiceBodyContext.class,i);
		}
		public ServiceSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceSpecContext serviceSpec() throws RecognitionException {
		ServiceSpecContext _localctx = new ServiceSpecContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_serviceSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(SERVICE);
			setState(311);
			serviceName();
			setState(312);
			match(LBRACE);
			setState(314); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(313);
				serviceBody();
				}
				}
				setState(316); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ATSERVER) | (1L << ATDOC) | (1L << ATHANDLER))) != 0) );
			setState(318);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(ApiParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ApiParser.IDENT, i);
		}
		public List<TerminalNode> BAR() { return getTokens(ApiParser.BAR); }
		public TerminalNode BAR(int i) {
			return getToken(ApiParser.BAR, i);
		}
		public ServiceNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceNameContext serviceName() throws RecognitionException {
		ServiceNameContext _localctx = new ServiceNameContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_serviceName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(320);
				match(IDENT);
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BAR) {
					{
					setState(321);
					match(BAR);
					}
				}

				}
				}
				setState(326); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceBodyContext extends ParserRuleContext {
		public ServiceRouteContext serviceRoute() {
			return getRuleContext(ServiceRouteContext.class,0);
		}
		public ServiceHandlerContext serviceHandler() {
			return getRuleContext(ServiceHandlerContext.class,0);
		}
		public ServiceHandlerNewContext serviceHandlerNew() {
			return getRuleContext(ServiceHandlerNewContext.class,0);
		}
		public ServiceDocContext serviceDoc() {
			return getRuleContext(ServiceDocContext.class,0);
		}
		public ServiceDocNewContext serviceDocNew() {
			return getRuleContext(ServiceDocNewContext.class,0);
		}
		public ServiceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceBodyContext serviceBody() throws RecognitionException {
		ServiceBodyContext _localctx = new ServiceBodyContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_serviceBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(328);
				serviceDoc();
				}
				break;
			case 2:
				{
				setState(329);
				serviceDocNew();
				}
				break;
			}
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATSERVER:
				{
				setState(332);
				serviceHandler();
				}
				break;
			case ATHANDLER:
				{
				setState(333);
				serviceHandlerNew();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(336);
			serviceRoute();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceDocContext extends ParserRuleContext {
		public TerminalNode ATDOC() { return getToken(ApiParser.ATDOC, 0); }
		public TerminalNode LPAREN() { return getToken(ApiParser.LPAREN, 0); }
		public PairContext pair() {
			return getRuleContext(PairContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ApiParser.RPAREN, 0); }
		public ServiceDocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceDoc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceDoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceDoc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceDoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceDocContext serviceDoc() throws RecognitionException {
		ServiceDocContext _localctx = new ServiceDocContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_serviceDoc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(ATDOC);
			setState(339);
			match(LPAREN);
			setState(340);
			pair();
			setState(341);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceDocNewContext extends ParserRuleContext {
		public TerminalNode ATDOC() { return getToken(ApiParser.ATDOC, 0); }
		public DocValueContext docValue() {
			return getRuleContext(DocValueContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(ApiParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ApiParser.RPAREN, 0); }
		public ServiceDocNewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceDocNew; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceDocNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceDocNew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceDocNew(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceDocNewContext serviceDocNew() throws RecognitionException {
		ServiceDocNewContext _localctx = new ServiceDocNewContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_serviceDocNew);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(ATDOC);
			setState(349);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUE:
				{
				setState(344);
				docValue();
				}
				break;
			case LPAREN:
				{
				{
				setState(345);
				match(LPAREN);
				setState(346);
				docValue();
				setState(347);
				match(RPAREN);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceHandlerContext extends ParserRuleContext {
		public TerminalNode ATSERVER() { return getToken(ApiParser.ATSERVER, 0); }
		public TerminalNode LPAREN() { return getToken(ApiParser.LPAREN, 0); }
		public HandlerPairContext handlerPair() {
			return getRuleContext(HandlerPairContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ApiParser.RPAREN, 0); }
		public ServiceHandlerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceHandler; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceHandler(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceHandler(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceHandler(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceHandlerContext serviceHandler() throws RecognitionException {
		ServiceHandlerContext _localctx = new ServiceHandlerContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_serviceHandler);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351);
			match(ATSERVER);
			setState(352);
			match(LPAREN);
			setState(353);
			handlerPair();
			setState(354);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceHandlerNewContext extends ParserRuleContext {
		public TerminalNode ATHANDLER() { return getToken(ApiParser.ATHANDLER, 0); }
		public HandlerValueContext handlerValue() {
			return getRuleContext(HandlerValueContext.class,0);
		}
		public ServiceHandlerNewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceHandlerNew; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceHandlerNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceHandlerNew(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceHandlerNew(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceHandlerNewContext serviceHandlerNew() throws RecognitionException {
		ServiceHandlerNewContext _localctx = new ServiceHandlerNewContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_serviceHandlerNew);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			match(ATHANDLER);
			setState(357);
			handlerValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ServiceRouteContext extends ParserRuleContext {
		public HttpRouteContext httpRoute() {
			return getRuleContext(HttpRouteContext.class,0);
		}
		public List<TerminalNode> LPAREN() { return getTokens(ApiParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(ApiParser.LPAREN, i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(ApiParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(ApiParser.RPAREN, i);
		}
		public TerminalNode RETURNS() { return getToken(ApiParser.RETURNS, 0); }
		public TerminalNode SMICOLON() { return getToken(ApiParser.SMICOLON, 0); }
		public List<BodyContext> body() {
			return getRuleContexts(BodyContext.class);
		}
		public BodyContext body(int i) {
			return getRuleContext(BodyContext.class,i);
		}
		public ServiceRouteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_serviceRoute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterServiceRoute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitServiceRoute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitServiceRoute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ServiceRouteContext serviceRoute() throws RecognitionException {
		ServiceRouteContext _localctx = new ServiceRouteContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_serviceRoute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			httpRoute();
			setState(365);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(360);
				match(LPAREN);
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 5)) & ~0x3f) == 0 && ((1L << (_la - 5)) & ((1L << (ANY - 5)) | (1L << (GOTYPE - 5)) | (1L << (LBRACK - 5)) | (1L << (STAR - 5)) | (1L << (IDENT - 5)))) != 0)) {
					{
					setState(361);
					body();
					}
				}

				setState(364);
				match(RPAREN);
				}
			}

			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(367);
				match(RETURNS);
				setState(368);
				match(LPAREN);
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 5)) & ~0x3f) == 0 && ((1L << (_la - 5)) & ((1L << (ANY - 5)) | (1L << (GOTYPE - 5)) | (1L << (LBRACK - 5)) | (1L << (STAR - 5)) | (1L << (IDENT - 5)))) != 0)) {
					{
					setState(369);
					body();
					}
				}

				setState(372);
				match(RPAREN);
				}
			}

			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SMICOLON) {
				{
				setState(375);
				match(SMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HttpRouteContext extends ParserRuleContext {
		public TerminalNode HTTPMETHOD() { return getToken(ApiParser.HTTPMETHOD, 0); }
		public TerminalNode PATH() { return getToken(ApiParser.PATH, 0); }
		public HttpRouteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_httpRoute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterHttpRoute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitHttpRoute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitHttpRoute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HttpRouteContext httpRoute() throws RecognitionException {
		HttpRouteContext _localctx = new HttpRouteContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_httpRoute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(HTTPMETHOD);
			setState(379);
			match(PATH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentPairContext extends ParserRuleContext {
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(ApiParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ApiParser.COLON, i);
		}
		public List<TerminalNode> DURATION() { return getTokens(ApiParser.DURATION); }
		public TerminalNode DURATION(int i) {
			return getToken(ApiParser.DURATION, i);
		}
		public List<IdentValueContext> identValue() {
			return getRuleContexts(IdentValueContext.class);
		}
		public IdentValueContext identValue(int i) {
			return getRuleContext(IdentValueContext.class,i);
		}
		public List<TerminalNode> PATH() { return getTokens(ApiParser.PATH); }
		public TerminalNode PATH(int i) {
			return getToken(ApiParser.PATH, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(ApiParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(ApiParser.NUMBER, i);
		}
		public List<TerminalNode> RAW_STRING() { return getTokens(ApiParser.RAW_STRING); }
		public TerminalNode RAW_STRING(int i) {
			return getToken(ApiParser.RAW_STRING, i);
		}
		public List<TerminalNode> VALUE() { return getTokens(ApiParser.VALUE); }
		public TerminalNode VALUE(int i) {
			return getToken(ApiParser.VALUE, i);
		}
		public List<TerminalNode> IDENT() { return getTokens(ApiParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ApiParser.IDENT, i);
		}
		public IdentPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterIdentPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitIdentPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitIdentPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentPairContext identPair() throws RecognitionException {
		IdentPairContext _localctx = new IdentPairContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_identPair);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(381);
				key();
				setState(382);
				match(COLON);
				setState(390);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
				case 1:
					{
					setState(383);
					match(DURATION);
					}
					break;
				case 2:
					{
					setState(384);
					identValue();
					}
					break;
				case 3:
					{
					setState(385);
					match(PATH);
					}
					break;
				case 4:
					{
					setState(386);
					match(NUMBER);
					}
					break;
				case 5:
					{
					setState(387);
					match(RAW_STRING);
					}
					break;
				case 6:
					{
					setState(388);
					match(VALUE);
					}
					break;
				case 7:
					{
					setState(389);
					match(IDENT);
					}
					break;
				}
				}
				}
				setState(396);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HandlerPairContext extends ParserRuleContext {
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(ApiParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ApiParser.COLON, i);
		}
		public List<HandlerValueContext> handlerValue() {
			return getRuleContexts(HandlerValueContext.class);
		}
		public HandlerValueContext handlerValue(int i) {
			return getRuleContext(HandlerValueContext.class,i);
		}
		public HandlerPairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_handlerPair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterHandlerPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitHandlerPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitHandlerPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HandlerPairContext handlerPair() throws RecognitionException {
		HandlerPairContext _localctx = new HandlerPairContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_handlerPair);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(397);
				key();
				setState(398);
				match(COLON);
				setState(399);
				handlerValue();
				}
				}
				setState(403); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentValueContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(ApiParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ApiParser.IDENT, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ApiParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ApiParser.COMMA, i);
		}
		public IdentValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterIdentValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitIdentValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitIdentValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentValueContext identValue() throws RecognitionException {
		IdentValueContext _localctx = new IdentValueContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_identValue);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(409); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(405);
					match(IDENT);
					setState(407);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(406);
						match(COMMA);
						}
					}

					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(411); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HandlerValueContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(ApiParser.VALUE, 0); }
		public TerminalNode RAW_STRING() { return getToken(ApiParser.RAW_STRING, 0); }
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public HandlerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_handlerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterHandlerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitHandlerValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitHandlerValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HandlerValueContext handlerValue() throws RecognitionException {
		HandlerValueContext _localctx = new HandlerValueContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_handlerValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			_la = _input.LA(1);
			if ( !(((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (IDENT - 66)) | (1L << (VALUE - 66)) | (1L << (RAW_STRING - 66)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportValueContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(ApiParser.VALUE, 0); }
		public TerminalNode AS() { return getToken(ApiParser.AS, 0); }
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public ImportValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterImportValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitImportValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitImportValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportValueContext importValue() throws RecognitionException {
		ImportValueContext _localctx = new ImportValueContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_importValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(VALUE);
			setState(418);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(416);
				match(AS);
				setState(417);
				match(IDENT);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DocValueContext extends ParserRuleContext {
		public TerminalNode VALUE() { return getToken(ApiParser.VALUE, 0); }
		public DocValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterDocValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitDocValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitDocValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DocValueContext docValue() throws RecognitionException {
		DocValueContext _localctx = new DocValueContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_docValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(VALUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public List<TerminalNode> COLON() { return getTokens(ApiParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(ApiParser.COLON, i);
		}
		public List<TerminalNode> VALUE() { return getTokens(ApiParser.VALUE); }
		public TerminalNode VALUE(int i) {
			return getToken(ApiParser.VALUE, i);
		}
		public List<TerminalNode> RAW_STRING() { return getTokens(ApiParser.RAW_STRING); }
		public TerminalNode RAW_STRING(int i) {
			return getToken(ApiParser.RAW_STRING, i);
		}
		public List<TerminalNode> IDENT() { return getTokens(ApiParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ApiParser.IDENT, i);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_pair);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IDENT) {
				{
				{
				setState(422);
				key();
				setState(423);
				match(COLON);
				setState(425);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(424);
					_la = _input.LA(1);
					if ( !(((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (IDENT - 66)) | (1L << (VALUE - 66)) | (1L << (RAW_STRING - 66)))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				}
				}
				}
				setState(431);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeyContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ApiParser.IDENT, 0); }
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ApiParserListener ) ((ApiParserListener)listener).exitKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ApiParserVisitor ) return ((ApiParserVisitor<? extends T>)visitor).visitKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_key);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432);
			match(IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 18:
			return typeFiled_sempred((TypeFiledContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean typeFiled_sempred(TypeFiledContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return isNormalField();
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001H\u01b3\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u0001\u0000\u0003\u0000j\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0005\u0000o\b\u0000\n\u0000\f\u0000r\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0003\u0002|\b\u0002\u0001\u0003\u0004\u0003\u007f"+
		"\b\u0003\u000b\u0003\f\u0003\u0080\u0001\u0004\u0001\u0004\u0003\u0004"+
		"\u0085\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006\u008d\b\u0006\n\u0006\f\u0006\u0090\t\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0003\b\u009b\b\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000b\u00a6\b"+
		"\u000b\n\u000b\f\u000b\u00a9\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00af\b\f\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u00b7\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00c2\b\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00c6\b\u0010\u0001"+
		"\u0011\u0001\u0011\u0003\u0011\u00ca\b\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00d2\b\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u00d7\b\u0013\n\u0013\f\u0013"+
		"\u00da\t\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u00de\b\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00e4\b\u0014\u0001"+
		"\u0015\u0003\u0015\u00e7\b\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00eb"+
		"\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u00f3\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0003\u0019\u0100\b\u0019\u0001\u0019\u0004\u0019\u0103"+
		"\b\u0019\u000b\u0019\f\u0019\u0104\u0001\u0019\u0001\u0019\u0001\u001a"+
		"\u0001\u001a\u0003\u001a\u010b\b\u001a\u0001\u001a\u0001\u001a\u0005\u001a"+
		"\u010f\b\u001a\n\u001a\f\u001a\u0112\t\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0003\u001d\u011b"+
		"\b\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001f\u0001\u001f\u0001 \u0001 \u0003 \u0126\b \u0001 \u0003 \u0129\b"+
		" \u0001 \u0001 \u0001!\u0003!\u012e\b!\u0001!\u0001!\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001#\u0001#\u0001#\u0001#\u0004#\u013b\b#\u000b#"+
		"\f#\u013c\u0001#\u0001#\u0001$\u0001$\u0003$\u0143\b$\u0004$\u0145\b$"+
		"\u000b$\f$\u0146\u0001%\u0001%\u0003%\u014b\b%\u0001%\u0001%\u0003%\u014f"+
		"\b%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0003\'\u015e\b\'\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001)\u0001)\u0001)\u0001*\u0001*\u0001*\u0003*\u016b\b*\u0001"+
		"*\u0003*\u016e\b*\u0001*\u0001*\u0001*\u0003*\u0173\b*\u0001*\u0003*\u0176"+
		"\b*\u0001*\u0003*\u0179\b*\u0001+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001"+
		",\u0001,\u0001,\u0001,\u0001,\u0001,\u0003,\u0187\b,\u0005,\u0189\b,\n"+
		",\f,\u018c\t,\u0001-\u0001-\u0001-\u0001-\u0004-\u0192\b-\u000b-\f-\u0193"+
		"\u0001.\u0001.\u0003.\u0198\b.\u0004.\u019a\b.\u000b.\f.\u019b\u0001/"+
		"\u0001/\u00010\u00010\u00010\u00030\u01a3\b0\u00011\u00011\u00012\u0001"+
		"2\u00012\u00032\u01aa\b2\u00052\u01ac\b2\n2\f2\u01af\t2\u00013\u00013"+
		"\u00013\u0000\u00004\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdf\u0000"+
		"\u0004\u0001\u0000\u0007\t\u0002\u0000@@BB\u0003\u0000\u0005\u0005\u001b"+
		"\u001bBB\u0002\u0000BBDE\u01bd\u0000i\u0001\u0000\u0000\u0000\u0002u\u0001"+
		"\u0000\u0000\u0000\u0004{\u0001\u0000\u0000\u0000\u0006~\u0001\u0000\u0000"+
		"\u0000\b\u0084\u0001\u0000\u0000\u0000\n\u0086\u0001\u0000\u0000\u0000"+
		"\f\u0089\u0001\u0000\u0000\u0000\u000e\u0093\u0001\u0000\u0000\u0000\u0010"+
		"\u009a\u0001\u0000\u0000\u0000\u0012\u009c\u0001\u0000\u0000\u0000\u0014"+
		"\u00a1\u0001\u0000\u0000\u0000\u0016\u00a7\u0001\u0000\u0000\u0000\u0018"+
		"\u00ae\u0001\u0000\u0000\u0000\u001a\u00b0\u0001\u0000\u0000\u0000\u001c"+
		"\u00b3\u0001\u0000\u0000\u0000\u001e\u00ba\u0001\u0000\u0000\u0000 \u00bd"+
		"\u0001\u0000\u0000\u0000\"\u00c7\u0001\u0000\u0000\u0000$\u00d1\u0001"+
		"\u0000\u0000\u0000&\u00d3\u0001\u0000\u0000\u0000(\u00e3\u0001\u0000\u0000"+
		"\u0000*\u00e6\u0001\u0000\u0000\u0000,\u00f2\u0001\u0000\u0000\u0000."+
		"\u00f4\u0001\u0000\u0000\u00000\u00f7\u0001\u0000\u0000\u00002\u0102\u0001"+
		"\u0000\u0000\u00004\u0108\u0001\u0000\u0000\u00006\u0115\u0001\u0000\u0000"+
		"\u00008\u0117\u0001\u0000\u0000\u0000:\u011a\u0001\u0000\u0000\u0000<"+
		"\u011e\u0001\u0000\u0000\u0000>\u0121\u0001\u0000\u0000\u0000@\u0125\u0001"+
		"\u0000\u0000\u0000B\u012d\u0001\u0000\u0000\u0000D\u0131\u0001\u0000\u0000"+
		"\u0000F\u0136\u0001\u0000\u0000\u0000H\u0144\u0001\u0000\u0000\u0000J"+
		"\u014a\u0001\u0000\u0000\u0000L\u0152\u0001\u0000\u0000\u0000N\u0157\u0001"+
		"\u0000\u0000\u0000P\u015f\u0001\u0000\u0000\u0000R\u0164\u0001\u0000\u0000"+
		"\u0000T\u0167\u0001\u0000\u0000\u0000V\u017a\u0001\u0000\u0000\u0000X"+
		"\u018a\u0001\u0000\u0000\u0000Z\u0191\u0001\u0000\u0000\u0000\\\u0199"+
		"\u0001\u0000\u0000\u0000^\u019d\u0001\u0000\u0000\u0000`\u019f\u0001\u0000"+
		"\u0000\u0000b\u01a4\u0001\u0000\u0000\u0000d\u01ad\u0001\u0000\u0000\u0000"+
		"f\u01b0\u0001\u0000\u0000\u0000hj\u0003\u0002\u0001\u0000ih\u0001\u0000"+
		"\u0000\u0000ij\u0001\u0000\u0000\u0000jp\u0001\u0000\u0000\u0000ko\u0003"+
		"\u0006\u0003\u0000lo\u0003\u000e\u0007\u0000mo\u0003\u0004\u0002\u0000"+
		"nk\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000nm\u0001\u0000\u0000"+
		"\u0000or\u0001\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000"+
		"\u0000\u0000qs\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000st\u0005"+
		"\u0000\u0000\u0001t\u0001\u0001\u0000\u0000\u0000uv\u0005\u0001\u0000"+
		"\u0000vw\u0005=\u0000\u0000wx\u0005D\u0000\u0000x\u0003\u0001\u0000\u0000"+
		"\u0000y|\u0003\u0010\b\u0000z|\u0003B!\u0000{y\u0001\u0000\u0000\u0000"+
		"{z\u0001\u0000\u0000\u0000|\u0005\u0001\u0000\u0000\u0000}\u007f\u0003"+
		"\b\u0004\u0000~}\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000"+
		"\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000"+
		"\u0081\u0007\u0001\u0000\u0000\u0000\u0082\u0085\u0003\n\u0005\u0000\u0083"+
		"\u0085\u0003\f\u0006\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0083"+
		"\u0001\u0000\u0000\u0000\u0085\t\u0001\u0000\u0000\u0000\u0086\u0087\u0005"+
		"\u000f\u0000\u0000\u0087\u0088\u0003`0\u0000\u0088\u000b\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0005\u000f\u0000\u0000\u008a\u008e\u00052\u0000\u0000"+
		"\u008b\u008d\u0003`0\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0090"+
		"\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0001\u0000\u0000\u0000\u008f\u0091\u0001\u0000\u0000\u0000\u0090\u008e"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u00053\u0000\u0000\u0092\r\u0001"+
		"\u0000\u0000\u0000\u0093\u0094\u0005\u0002\u0000\u0000\u0094\u0095\u0005"+
		"2\u0000\u0000\u0095\u0096\u0003d2\u0000\u0096\u0097\u00053\u0000\u0000"+
		"\u0097\u000f\u0001\u0000\u0000\u0000\u0098\u009b\u0003\u0018\f\u0000\u0099"+
		"\u009b\u0003\u0012\t\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u0099"+
		"\u0001\u0000\u0000\u0000\u009b\u0011\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0003\u0014\n\u0000\u009d\u009e\u00052\u0000\u0000\u009e\u009f\u0003"+
		"\u0016\u000b\u0000\u009f\u00a0\u00053\u0000\u0000\u00a0\u0013\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0007\u0000\u0000\u0000\u00a2\u0015\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a6\u0003\"\u0011\u0000\u00a4\u00a6\u00034\u001a"+
		"\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a4\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a9\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u0017\u0001\u0000\u0000"+
		"\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00aa\u00af\u0003\u001c\u000e"+
		"\u0000\u00ab\u00af\u0003\u001a\r\u0000\u00ac\u00af\u0003\u001e\u000f\u0000"+
		"\u00ad\u00af\u0003 \u0010\u0000\u00ae\u00aa\u0001\u0000\u0000\u0000\u00ae"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae"+
		"\u00ad\u0001\u0000\u0000\u0000\u00af\u0019\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b1\u0005\u0007\u0000\u0000\u00b1\u00b2\u00034\u001a\u0000\u00b2\u001b"+
		"\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005\u0007\u0000\u0000\u00b4\u00b6"+
		"\u00036\u001b\u0000\u00b5\u00b7\u0005=\u0000\u0000\u00b6\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u0003,\u0016\u0000\u00b9\u001d\u0001\u0000\u0000"+
		"\u0000\u00ba\u00bb\u0005\b\u0000\u0000\u00bb\u00bc\u00034\u001a\u0000"+
		"\u00bc\u001f\u0001\u0000\u0000\u0000\u00bd\u00c5\u0005\t\u0000\u0000\u00be"+
		"\u00c6\u00034\u001a\u0000\u00bf\u00c1\u00036\u001b\u0000\u00c0\u00c2\u0005"+
		"=\u0000\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c4\u0003,\u0016"+
		"\u0000\u00c4\u00c6\u0001\u0000\u0000\u0000\u00c5\u00be\u0001\u0000\u0000"+
		"\u0000\u00c5\u00bf\u0001\u0000\u0000\u0000\u00c6!\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c9\u00036\u001b\u0000\u00c8\u00ca\u0005=\u0000\u0000\u00c9\u00c8"+
		"\u0001\u0000\u0000\u0000\u00c9\u00ca\u0001\u0000\u0000\u0000\u00ca\u00cb"+
		"\u0001\u0000\u0000\u0000\u00cb\u00cc\u0003,\u0016\u0000\u00cc#\u0001\u0000"+
		"\u0000\u0000\u00cd\u00ce\u0004\u0012\u0000\u0000\u00ce\u00d2\u0003&\u0013"+
		"\u0000\u00cf\u00d2\u0003*\u0015\u0000\u00d0\u00d2\u00034\u001a\u0000\u00d1"+
		"\u00cd\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d2%\u0001\u0000\u0000\u0000\u00d3\u00d8"+
		"\u00038\u001c\u0000\u00d4\u00d5\u0005:\u0000\u0000\u00d5\u00d7\u00038"+
		"\u001c\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00da\u0001\u0000"+
		"\u0000\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000"+
		"\u0000\u0000\u00d9\u00db\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000"+
		"\u0000\u0000\u00db\u00dd\u0003(\u0014\u0000\u00dc\u00de\u0003>\u001f\u0000"+
		"\u00dd\u00dc\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000"+
		"\u00de\'\u0001\u0000\u0000\u0000\u00df\u00e4\u0003,\u0016\u0000\u00e0"+
		"\u00e4\u0003.\u0017\u0000\u00e1\u00e4\u00030\u0018\u0000\u00e2\u00e4\u0003"+
		"2\u0019\u0000\u00e3\u00df\u0001\u0000\u0000\u0000\u00e3\u00e0\u0001\u0000"+
		"\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e2\u0001\u0000"+
		"\u0000\u0000\u00e4)\u0001\u0000\u0000\u0000\u00e5\u00e7\u0005;\u0000\u0000"+
		"\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00ea\u0003:\u001d\u0000\u00e9"+
		"\u00eb\u0003>\u001f\u0000\u00ea\u00e9\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0001\u0000\u0000\u0000\u00eb+\u0001\u0000\u0000\u0000\u00ec\u00f3\u0005"+
		"\u001b\u0000\u0000\u00ed\u00f3\u0003:\u001d\u0000\u00ee\u00f3\u0005\u0005"+
		"\u0000\u0000\u00ef\u00f0\u0005\u0006\u0000\u0000\u00f0\u00f1\u00054\u0000"+
		"\u0000\u00f1\u00f3\u00055\u0000\u0000\u00f2\u00ec\u0001\u0000\u0000\u0000"+
		"\u00f2\u00ed\u0001\u0000\u0000\u0000\u00f2\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f2\u00ef\u0001\u0000\u0000\u0000\u00f3-\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f5\u0005;\u0000\u0000\u00f5\u00f6\u0003,\u0016\u0000\u00f6/\u0001"+
		"\u0000\u0000\u0000\u00f7\u00f8\u0005\u0003\u0000\u0000\u00f8\u00f9\u0005"+
		"6\u0000\u0000\u00f9\u00fa\u0003(\u0014\u0000\u00fa\u00fb\u00057\u0000"+
		"\u0000\u00fb\u00fc\u0003(\u0014\u0000\u00fc1\u0001\u0000\u0000\u0000\u00fd"+
		"\u00ff\u00056\u0000\u0000\u00fe\u0100\u0007\u0001\u0000\u0000\u00ff\u00fe"+
		"\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0101"+
		"\u0001\u0000\u0000\u0000\u0101\u0103\u00057\u0000\u0000\u0102\u00fd\u0001"+
		"\u0000\u0000\u0000\u0103\u0104\u0001\u0000\u0000\u0000\u0104\u0102\u0001"+
		"\u0000\u0000\u0000\u0104\u0105\u0001\u0000\u0000\u0000\u0105\u0106\u0001"+
		"\u0000\u0000\u0000\u0106\u0107\u0003(\u0014\u0000\u01073\u0001\u0000\u0000"+
		"\u0000\u0108\u010a\u00036\u001b\u0000\u0109\u010b\u0005\u0004\u0000\u0000"+
		"\u010a\u0109\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000"+
		"\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u0110\u00054\u0000\u0000\u010d"+
		"\u010f\u0003$\u0012\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010f\u0112"+
		"\u0001\u0000\u0000\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0110\u0111"+
		"\u0001\u0000\u0000\u0000\u0111\u0113\u0001\u0000\u0000\u0000\u0112\u0110"+
		"\u0001\u0000\u0000\u0000\u0113\u0114\u00055\u0000\u0000\u01145\u0001\u0000"+
		"\u0000\u0000\u0115\u0116\u0005B\u0000\u0000\u01167\u0001\u0000\u0000\u0000"+
		"\u0117\u0118\u0005B\u0000\u0000\u01189\u0001\u0000\u0000\u0000\u0119\u011b"+
		"\u0003<\u001e\u0000\u011a\u0119\u0001\u0000\u0000\u0000\u011a\u011b\u0001"+
		"\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u011d\u0005"+
		"B\u0000\u0000\u011d;\u0001\u0000\u0000\u0000\u011e\u011f\u0005B\u0000"+
		"\u0000\u011f\u0120\u00058\u0000\u0000\u0120=\u0001\u0000\u0000\u0000\u0121"+
		"\u0122\u0005E\u0000\u0000\u0122?\u0001\u0000\u0000\u0000\u0123\u0124\u0005"+
		"6\u0000\u0000\u0124\u0126\u00057\u0000\u0000\u0125\u0123\u0001\u0000\u0000"+
		"\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0128\u0001\u0000\u0000"+
		"\u0000\u0127\u0129\u0005;\u0000\u0000\u0128\u0127\u0001\u0000\u0000\u0000"+
		"\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u012a\u0001\u0000\u0000\u0000"+
		"\u012a\u012b\u0007\u0002\u0000\u0000\u012bA\u0001\u0000\u0000\u0000\u012c"+
		"\u012e\u0003D\"\u0000\u012d\u012c\u0001\u0000\u0000\u0000\u012d\u012e"+
		"\u0001\u0000\u0000\u0000\u012e\u012f\u0001\u0000\u0000\u0000\u012f\u0130"+
		"\u0003F#\u0000\u0130C\u0001\u0000\u0000\u0000\u0131\u0132\u0005\n\u0000"+
		"\u0000\u0132\u0133\u00052\u0000\u0000\u0133\u0134\u0003X,\u0000\u0134"+
		"\u0135\u00053\u0000\u0000\u0135E\u0001\u0000\u0000\u0000\u0136\u0137\u0005"+
		"\r\u0000\u0000\u0137\u0138\u0003H$\u0000\u0138\u013a\u00054\u0000\u0000"+
		"\u0139\u013b\u0003J%\u0000\u013a\u0139\u0001\u0000\u0000\u0000\u013b\u013c"+
		"\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013c\u013d"+
		"\u0001\u0000\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u013f"+
		"\u00055\u0000\u0000\u013fG\u0001\u0000\u0000\u0000\u0140\u0142\u0005B"+
		"\u0000\u0000\u0141\u0143\u0005<\u0000\u0000\u0142\u0141\u0001\u0000\u0000"+
		"\u0000\u0142\u0143\u0001\u0000\u0000\u0000\u0143\u0145\u0001\u0000\u0000"+
		"\u0000\u0144\u0140\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000"+
		"\u0000\u0146\u0144\u0001\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000"+
		"\u0000\u0147I\u0001\u0000\u0000\u0000\u0148\u014b\u0003L&\u0000\u0149"+
		"\u014b\u0003N\'\u0000\u014a\u0148\u0001\u0000\u0000\u0000\u014a\u0149"+
		"\u0001\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000\u0000\u014b\u014e"+
		"\u0001\u0000\u0000\u0000\u014c\u014f\u0003P(\u0000\u014d\u014f\u0003R"+
		")\u0000\u014e\u014c\u0001\u0000\u0000\u0000\u014e\u014d\u0001\u0000\u0000"+
		"\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0151\u0003T*\u0000\u0151"+
		"K\u0001\u0000\u0000\u0000\u0152\u0153\u0005\u000b\u0000\u0000\u0153\u0154"+
		"\u00052\u0000\u0000\u0154\u0155\u0003d2\u0000\u0155\u0156\u00053\u0000"+
		"\u0000\u0156M\u0001\u0000\u0000\u0000\u0157\u015d\u0005\u000b\u0000\u0000"+
		"\u0158\u015e\u0003b1\u0000\u0159\u015a\u00052\u0000\u0000\u015a\u015b"+
		"\u0003b1\u0000\u015b\u015c\u00053\u0000\u0000\u015c\u015e\u0001\u0000"+
		"\u0000\u0000\u015d\u0158\u0001\u0000\u0000\u0000\u015d\u0159\u0001\u0000"+
		"\u0000\u0000\u015eO\u0001\u0000\u0000\u0000\u015f\u0160\u0005\n\u0000"+
		"\u0000\u0160\u0161\u00052\u0000\u0000\u0161\u0162\u0003Z-\u0000\u0162"+
		"\u0163\u00053\u0000\u0000\u0163Q\u0001\u0000\u0000\u0000\u0164\u0165\u0005"+
		"\f\u0000\u0000\u0165\u0166\u0003^/\u0000\u0166S\u0001\u0000\u0000\u0000"+
		"\u0167\u016d\u0003V+\u0000\u0168\u016a\u00052\u0000\u0000\u0169\u016b"+
		"\u0003@ \u0000\u016a\u0169\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000"+
		"\u0000\u0000\u016b\u016c\u0001\u0000\u0000\u0000\u016c\u016e\u00053\u0000"+
		"\u0000\u016d\u0168\u0001\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000"+
		"\u0000\u016e\u0175\u0001\u0000\u0000\u0000\u016f\u0170\u0005\u000e\u0000"+
		"\u0000\u0170\u0172\u00052\u0000\u0000\u0171\u0173\u0003@ \u0000\u0172"+
		"\u0171\u0001\u0000\u0000\u0000\u0172\u0173\u0001\u0000\u0000\u0000\u0173"+
		"\u0174\u0001\u0000\u0000\u0000\u0174\u0176\u00053\u0000\u0000\u0175\u016f"+
		"\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u0178"+
		"\u0001\u0000\u0000\u0000\u0177\u0179\u00059\u0000\u0000\u0178\u0177\u0001"+
		"\u0000\u0000\u0000\u0178\u0179\u0001\u0000\u0000\u0000\u0179U\u0001\u0000"+
		"\u0000\u0000\u017a\u017b\u0005\u0011\u0000\u0000\u017b\u017c\u00050\u0000"+
		"\u0000\u017cW\u0001\u0000\u0000\u0000\u017d\u017e\u0003f3\u0000\u017e"+
		"\u0186\u0005>\u0000\u0000\u017f\u0187\u00051\u0000\u0000\u0180\u0187\u0003"+
		"\\.\u0000\u0181\u0187\u00050\u0000\u0000\u0182\u0187\u0005@\u0000\u0000"+
		"\u0183\u0187\u0005E\u0000\u0000\u0184\u0187\u0005D\u0000\u0000\u0185\u0187"+
		"\u0005B\u0000\u0000\u0186\u017f\u0001\u0000\u0000\u0000\u0186\u0180\u0001"+
		"\u0000\u0000\u0000\u0186\u0181\u0001\u0000\u0000\u0000\u0186\u0182\u0001"+
		"\u0000\u0000\u0000\u0186\u0183\u0001\u0000\u0000\u0000\u0186\u0184\u0001"+
		"\u0000\u0000\u0000\u0186\u0185\u0001\u0000\u0000\u0000\u0187\u0189\u0001"+
		"\u0000\u0000\u0000\u0188\u017d\u0001\u0000\u0000\u0000\u0189\u018c\u0001"+
		"\u0000\u0000\u0000\u018a\u0188\u0001\u0000\u0000\u0000\u018a\u018b\u0001"+
		"\u0000\u0000\u0000\u018bY\u0001\u0000\u0000\u0000\u018c\u018a\u0001\u0000"+
		"\u0000\u0000\u018d\u018e\u0003f3\u0000\u018e\u018f\u0005>\u0000\u0000"+
		"\u018f\u0190\u0003^/\u0000\u0190\u0192\u0001\u0000\u0000\u0000\u0191\u018d"+
		"\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0191"+
		"\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000\u0194[\u0001"+
		"\u0000\u0000\u0000\u0195\u0197\u0005B\u0000\u0000\u0196\u0198\u0005:\u0000"+
		"\u0000\u0197\u0196\u0001\u0000\u0000\u0000\u0197\u0198\u0001\u0000\u0000"+
		"\u0000\u0198\u019a\u0001\u0000\u0000\u0000\u0199\u0195\u0001\u0000\u0000"+
		"\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u0199\u0001\u0000\u0000"+
		"\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019c]\u0001\u0000\u0000\u0000"+
		"\u019d\u019e\u0007\u0003\u0000\u0000\u019e_\u0001\u0000\u0000\u0000\u019f"+
		"\u01a2\u0005D\u0000\u0000\u01a0\u01a1\u0005\u0010\u0000\u0000\u01a1\u01a3"+
		"\u0005B\u0000\u0000\u01a2\u01a0\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001"+
		"\u0000\u0000\u0000\u01a3a\u0001\u0000\u0000\u0000\u01a4\u01a5\u0005D\u0000"+
		"\u0000\u01a5c\u0001\u0000\u0000\u0000\u01a6\u01a7\u0003f3\u0000\u01a7"+
		"\u01a9\u0005>\u0000\u0000\u01a8\u01aa\u0007\u0003\u0000\u0000\u01a9\u01a8"+
		"\u0001\u0000\u0000\u0000\u01a9\u01aa\u0001\u0000\u0000\u0000\u01aa\u01ac"+
		"\u0001\u0000\u0000\u0000\u01ab\u01a6\u0001\u0000\u0000\u0000\u01ac\u01af"+
		"\u0001\u0000\u0000\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ad\u01ae"+
		"\u0001\u0000\u0000\u0000\u01aee\u0001\u0000\u0000\u0000\u01af\u01ad\u0001"+
		"\u0000\u0000\u0000\u01b0\u01b1\u0005B\u0000\u0000\u01b1g\u0001\u0000\u0000"+
		"\u00001inp{\u0080\u0084\u008e\u009a\u00a5\u00a7\u00ae\u00b6\u00c1\u00c5"+
		"\u00c9\u00d1\u00d8\u00dd\u00e3\u00e6\u00ea\u00f2\u00ff\u0104\u010a\u0110"+
		"\u011a\u0125\u0128\u012d\u013c\u0142\u0146\u014a\u014e\u015d\u016a\u016d"+
		"\u0172\u0175\u0178\u0186\u018a\u0193\u0197\u019b\u01a2\u01a9\u01ad";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}