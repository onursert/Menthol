package org.onursert.obss.menthol.service;

import org.apache.commons.lang3.text.WordUtils;
import org.onursert.obss.menthol.model.SubSubject;
import org.onursert.obss.menthol.model.Subject;
import org.onursert.obss.menthol.repository.SubSubjectRepository;
import org.onursert.obss.menthol.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminSubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubSubjectRepository subSubjectRepository;

    public String adminSubject() {
        return "redirect:/admin/subject/list";
    }

    public String adminSubjectList(Model model, String error, String success) {
        if (success != null) {
            model.addAttribute("success", WordUtils.capitalizeFully(success.replaceAll("_", " ")));
        }
        if (error != null) {
            model.addAttribute("error", WordUtils.capitalizeFully(error.replaceAll("_", " ")));
        }
        model.addAttribute("subjects", subjectRepository.findAll());
        return "admin/subject/list";
    }

    public String adminSubjectEdit(Model model, Integer id) {
        model.addAttribute("subject", subjectRepository.findById(id).orElse(null));
        return "admin/subject/edit";
    }

    public String adminSubjectUpdate(Subject subject) {
        if (subject.getName().equals("")) {
            return "redirect:/admin/subject/list?error=update";
        }
        subjectRepository.save(subject);
        return "redirect:/admin/subject/list?success=update";
    }

    public String adminSubjectDelete(Integer id) {
        try {
            subjectRepository.deleteById(id);
        } catch (Exception e) {
            return "redirect:/admin/subject/list?error=delete";
        }
        return "redirect:/admin/subject/list?success=delete";
    }

    public String adminSubjectAdd() {
        return "admin/subject/add";
    }

    public String adminSubjectAdd(String subjectName, String subSubjects) {
        if (subjectName.equals("")) {
            return "redirect:/admin/subject/list?error=add";
        }

        Subject newSubject = new Subject();
        newSubject.setName(subjectName);
        subjectRepository.save(newSubject);

        String[] subSubjectList = subSubjects.split(",");
        for (String ss : subSubjectList) {
            SubSubject newSubSubject = new SubSubject();
            newSubSubject.setName(ss);
            newSubSubject.setSubject(newSubject);
            subSubjectRepository.save(newSubSubject);
        }

        return "redirect:/admin/subject/list?success=add";
    }
}
