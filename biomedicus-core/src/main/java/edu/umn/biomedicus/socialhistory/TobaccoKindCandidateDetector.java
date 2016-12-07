/*
 * Copyright (c) 2016 Regents of the University of Minnesota.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.umn.biomedicus.socialhistory;

import edu.umn.biomedicus.common.types.semantics.SubstanceUsageKind;
import edu.umn.biomedicus.common.types.text.ParseToken;
import edu.umn.biomedicus.common.types.text.TermToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TobaccoKindCandidateDetector implements KindCandidateDetector {

    private static final Logger LOGGER = LoggerFactory.getLogger(TobaccoKindCandidateDetector.class);
    private Helpers helper = new Helpers();

    @Override
    public boolean isSocialHistoryHeader(List<ParseToken> headerTokens) {


        String strHeaderList = "(history|complaint|habits|subjectiv|allerg|risk +factor|hpi|Present +illness|Current +level +of +activity|Present +illness|Cardiovascular +risk +analysis|tobacco)";
        String sttHeaderListExact = "(ESRD|SH|SHX)";

        String strHeader = helper.toTokensString(headerTokens);
        if (strHeader.matches("(?is).*\\b" + strHeaderList + "\\b.*") || strHeader.matches("(?s).*\\b" + sttHeaderListExact + "\\b.*") ) {return true;}

        return false;
    }

    @Override
    public boolean isSocialHistorySentence(List<ParseToken> sectionTitleTokens, List<ParseToken> sentenceTokens) {

        String strRelative = "(mom|mother|dad|father|grandmother|grandfather|son|child|daughter|sister|brother|maternal|paternal|niece|nephew|cousin|grandchild)";
        String strNicotine = "(smok|chew|dips|expo|smoker|Chews tobacco|Snuff user|Ex-smoker|non smoker|nonsmoker|cigarette|pack|tin|carton|tobacco|Nicorette)";

        String strSentence = helper.toTokensString(sentenceTokens);
        String strHeader = helper.toTokensString(sectionTitleTokens);

        strHeader = "SOCIAL HISTORY";

        if (! strSentence.matches(".*[a-zA-Z].*")) {
            return false;
        }
        if (strSentence== strHeader) {
            return false;
        }
        if (strSentence.matches("(?s).*:$")) {
            return false;
        }
        if (strSentence.matches("(?is).*\\b" + strRelative + "\\b.*")) {
            return false;
        }
        if (!strSentence.matches("(?is).*" + strNicotine + ".*")) {

            return false;
        }

        return true;
    }

    @Override
    public SubstanceUsageKind getSocialHistoryKind() {
        return SubstanceUsageKind.NICOTINE;
    }
}
